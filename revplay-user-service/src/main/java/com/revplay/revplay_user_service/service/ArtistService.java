package com.revplay.revplay_user_service.service;

import com.revplay.revplay_user_service.Enum.RoleName;
import com.revplay.revplay_user_service.dto.request.ArtistProfileRequest;
import com.revplay.revplay_user_service.dto.response.ArtistDTO;
import com.revplay.revplay_user_service.dto.response.ArtistResponse;
import com.revplay.revplay_user_service.model.Artist;
import com.revplay.revplay_user_service.model.User;
import com.revplay.revplay_user_service.repository.ArtistRepository;
import com.revplay.revplay_user_service.repository.RoleRepository;
import com.revplay.revplay_user_service.repository.UserRepository;
import com.revplay.revplay_user_service.transformer.ArtistTransformer;
import com.revplay.revplay_user_service.utils.SecurityUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ArtistService {

    private final ArtistRepository artistRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final CloudinaryService cloudinaryService;
    private final ArtistTransformer artistTransformer;

    public ArtistService(ArtistRepository artistRepository, UserRepository userRepository, RoleRepository roleRepository, CloudinaryService cloudinaryService, ArtistTransformer artistTransformer) {
        this.artistRepository = artistRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.cloudinaryService = cloudinaryService;
        this.artistTransformer = artistTransformer;
    }



    public String getCurrentEmail() {
        return SecurityUtils.getCurrentEmail();
    }

    public ArtistResponse updateArtistProfile(ArtistProfileRequest request, MultipartFile profilePicture,
                                              MultipartFile bannerImage) {


        User user = userRepository.findByEmail(getCurrentEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        boolean isArtist = user.getRoles()
                .stream()
                .anyMatch(role -> role.getName() == RoleName.ARTIST);

        if (!isArtist) {
            throw new RuntimeException("Only artists have profiles");
        }
        Artist artist = artistRepository.findByUserId(user.getId()).orElseThrow(
                () -> new RuntimeException("Artist not found")
        );

        if (profilePicture != null && !profilePicture.isEmpty()) {
            String profileUrl = cloudinaryService.uploadFile(
                    profilePicture,
                    "profile_images"
            );
            artist.setProfilePicture(profileUrl);
            System.out.println(profileUrl);
        }

        if (bannerImage != null && !bannerImage.isEmpty()) {
            String bannerUrl = cloudinaryService.uploadFile(
                    bannerImage,
                    "artists/banner"
            );
            artist.setBannerImage(bannerUrl);
            System.out.println(bannerUrl);
        }

        return artistTransformer.artistToArtistResponse(artistRepository.save(artistTransformer.setArtistProfile(artist, user, request)));
    }

    public  ArtistResponse getArtistProfile() {

        User user = userRepository.findByEmail(getCurrentEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Artist artist = artistRepository.findByUserId(user.getId())
                .orElseThrow(() -> new RuntimeException("Artist profile not found"));

        return artistTransformer.artistToArtistResponse(artistRepository.findById(artist.getId()).get());
    }

    public List<ArtistResponse> getAllArtist() {
        return artistRepository.findAll().stream().map(artistTransformer::artistToArtistResponse).toList();
    }

    public ArtistDTO getArtist(Long id) {
        Optional<Artist> artist =  artistRepository.findById(id);
        return artist.map(artistTransformer::artistToArtistDTO).orElse(null);
    }

    public Page<ArtistDTO> searchArtist(String q, Pageable pageable) {
        return artistRepository
                .findByArtistNameContainingIgnoreCase(q, pageable)
                .map(artistTransformer::artistToArtistDTO);
    }
}
