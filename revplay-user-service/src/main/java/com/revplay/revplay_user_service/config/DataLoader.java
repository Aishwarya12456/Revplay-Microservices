package com.revplay.revplay_user_service.config;

import com.revplay.revplay_user_service.Enum.RoleName;
import com.revplay.revplay_user_service.model.Role;
import com.revplay.revplay_user_service.model.User;
import com.revplay.revplay_user_service.repository.RoleRepository;
import com.revplay.revplay_user_service.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class DataLoader implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final JdbcTemplate jdbcTemplate;

    public DataLoader(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder, JdbcTemplate jdbcTemplate) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public void run(String... args) {
        try {
            String db = jdbcTemplate.queryForObject("SELECT DATABASE()", String.class);
            System.out.println("DataLoader connecting to DB: " + db);

            // Drop ALL triggers on both the users and artists tables
            for (String table : new String[]{"users", "artists"}) {
                java.util.List<String> triggers = jdbcTemplate.queryForList(
                    "SELECT TRIGGER_NAME FROM information_schema.TRIGGERS WHERE TRIGGER_SCHEMA = DATABASE() AND EVENT_OBJECT_TABLE = '" + table + "'",
                    String.class
                );
                for (String trigger : triggers) {
                    jdbcTemplate.execute("DROP TRIGGER IF EXISTS `" + trigger + "`");
                    System.out.println("Dropped trigger: " + trigger + " on table: " + table);
                }
            }

            // Drop any extra/legacy columns from users
            for (String col : new String[]{"name", "role"}) {
                try {
                    jdbcTemplate.execute("ALTER TABLE users DROP COLUMN `" + col + "`");
                    System.out.println("Dropped column '" + col + "' from users table.");
                } catch (Exception e) {
                    System.out.println("Column '" + col + "' not present in users (OK).");
                }
            }

            // Drop rogue columns from artists
            for (String col : new String[]{"name", "role"}) {
                try {
                    jdbcTemplate.execute("ALTER TABLE artists DROP COLUMN `" + col + "`");
                    System.out.println("Dropped column '" + col + "' from artists table.");
                } catch (Exception e) {
                    System.out.println("Column '" + col + "' not present in artists (OK).");
                }
            }
        } catch (Exception e) {
            System.err.println("Schema cleanup failed: " + e.getMessage());
        }



        if (roleRepository.count() == 0) {

            roleRepository.save(Role.builder().name(RoleName.LISTENER).build());
            roleRepository.save(Role.builder().name(RoleName.ARTIST).build());
            roleRepository.save(Role.builder().name(RoleName.ADMIN).build());
        }

        if (!userRepository.existsByUsername("admin")) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setEmail("admin@revplay.com");
            admin.setPassword(passwordEncoder.encode("admin123"));


            Role adminRole = roleRepository.findByName(RoleName.ADMIN)
                    .orElseThrow(() -> new RuntimeException("ADMIN role not found"));

            Role userRole = roleRepository.findByName(RoleName.LISTENER)
                    .orElseThrow(() -> new RuntimeException("USER role not found"));

            admin.setRoles(Set.of(adminRole, userRole));

            admin.setEnabled(true);

            userRepository.save(admin);

            System.out.println("Admin created → username: admin | password: admin123");
        }
    }
}
