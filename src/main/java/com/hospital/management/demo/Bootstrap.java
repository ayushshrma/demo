package com.hospital.management.demo;

import com.hospital.management.demo.entity.HospitalStaff;
import com.hospital.management.demo.entity.Role;
import com.hospital.management.demo.repositories.HospitalStaffRepository;
import com.hospital.management.demo.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Component
public class Bootstrap implements ApplicationRunner {

    protected final Log logger = LogFactory.getLog(getClass());

    private HospitalStaffRepository userRepository;

    private RoleRepository roleRepository;

    @Autowired
    public Bootstrap(HospitalStaffRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }



    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.info("BootstrapCommandLineRunner's run method started.");

        if(roleRepository.count()<1){
            Role role1 = new Role();
            role1.setRole_id(1L);
            role1.setAuthority("ADMIN");

            Role role2 = new Role();
            role2.setRole_id(2L);
            role2.setAuthority("Doctor");

            Role role3 = new Role();
            role3.setRole_id(3L);
            role3.setAuthority("Nurse");

            Role role4 = new Role();
            role3.setRole_id(3L);
            role3.setAuthority("Receptionist");

            roleRepository.save(role1);
            roleRepository.save(role2);
            roleRepository.save(role3);
        }
        logger.info("HospitalManagement Application : started Successfully");
    }
}