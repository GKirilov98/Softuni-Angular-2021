package softuni.angular;

import org.joda.time.DateTime;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import softuni.angular.data.entities.*;
import softuni.angular.services.impl.UserDetailsImpl;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Project: backend
 * Created by: GKirilov
 * On: 11/17/2021
 */
public class TestInitEntitiesUtils {
    public Client initTestClient(Long clientId, NClientType clientType) {
        Client client = new Client();
        client.setId(clientId);
        client.setFullName("Тестово име " + clientId);
        client.setEgnBulstat("1231231 " + clientId);
        client.setAddress("Тестови адрес " + clientId);
        client.setEmail("Тестови email " + clientId);
        client.setNote("Тестови note " + clientId);
        client.setPhoneNumber("Тестови phone_number " + clientId);
        client.setClientType(clientType);
        return client;
    }


    public List<Client> initTenClients() {
        List<Client> entities = new ArrayList<>();
        NClientType nClientType = this.initTestClientTypePerson();
        for (int i = 0; i < 10; i++) {
            entities.add(this.initTestClient((long) i * -1, nClientType));
        }

        return entities;
    }

    public NClientType initTestClientTypePerson(){
        NClientType nClientType = new NClientType();
        nClientType.setCode("PERSON");
        nClientType.setDescription("Тестови тип клиент физическо лице");
        nClientType.setId(-1L);
        return nClientType;
    }

    public NClientType initTestClientTypeLegal(){
        NClientType nClientType = new NClientType();
        nClientType.setCode("LEGAL");
        nClientType.setDescription("Тестови тип клиент юридическо лице");
        nClientType.setId(-1L);
        return nClientType;
    }

    private List<Role> initRoles() {
        List<Role> roles = new ArrayList<>();
        Role roleAdmin = new Role();
        roleAdmin.setId(-1L);
        roleAdmin.setCode("ADMIN");
        roleAdmin.setDescription("Администратор ТЕСТ");
        roles.add(roleAdmin);
        Role roleUser = new Role();
        roleUser.setId(-2L);
        roleUser.setCode("USER");
        roleUser.setDescription("Потребител ТЕСТ");
        roles.add(roleUser);
        return roles;
    }

    public void setAuthenticate() {
        List<Role> roles = this.initRoles();
        User user = new User();
        user.setId(-1L);
        user.setUsername("username_test");
        user.setPassword("password_test");
        user.setRoles(roles);

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        roles.forEach(e -> {
            authorities.add(new SimpleGrantedAuthority(e.getCode()));
        });

        UserDetails userDetails = new UserDetailsImpl(user.getId(), user.getUsername(), user.getPassword(), authorities);
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    public List<InsCompany> initTenInsCompany() {
        List<InsCompany> entities = new ArrayList<>();
        for (long i = 1; i <= 10; i++) {
            InsCompany entity = this.initTestInsCompany( (-1) * i);
            entities.add(entity);
        }
        return entities;
    }

    public InsCompany initTestInsCompany(Long companyId) {
        InsCompany entity = new InsCompany();
        entity.setId(companyId);
        entity.setName("Тест компания" + companyId);
        entity.setPhone("123141" + companyId);
        entity.setEmail("email@emai.com" + companyId);
        entity.setBulstat("9991231312" + companyId);
        entity.setAddress("Тестови адрес на компания" + companyId);
        return entity;
    }

    public NInsType initTestInsType(Long insTypeId) {
        NInsType entity = new NInsType();
        entity.setId(insTypeId);
        entity.setCode("INS_PROPERTY");
        entity.setDescription("Тест ЗАСТРАХОВКА на \"Имущество\"");
        return entity;
    }

    public List<InsProduct> initTenInsProductWithCompanyId(Long companyId) {
        List<InsProduct> entities = new ArrayList<>();
        for (long i = 1; i <= 10; i++) {
            long id = i * (-1);
            entities.add(this.initTestInsProduct(id, id , companyId ));
        }

        return entities;
    }

    public InsProduct initTestInsProduct(Long insProductId, Long insTypeId, Long insCompanyId) {
        InsProduct entity = new InsProduct();
        entity.setId(insProductId);
        entity.setName("Tест продукт");
        entity.setDefered(false);
        entity.setComissionPercent(BigDecimal.TEN);
        entity.setPremiumPercent(BigDecimal.ONE);
        entity.setInsType(this.initTestInsType(insTypeId));
        entity.setInsCompany(this.initTestInsCompany(insCompanyId));
        return entity;
    }

    public List<Policy> initTenPolicies() {
        List<Policy> policies = new ArrayList<>();
        for (long i = 1; i <= 10; i++) {
            Long id = (-1) * i;
            policies.add(this.initTestPolicy(id, id, id));
        }

        return policies;
    }

    public Policy initTestPolicy(Long policyId, Long insProductId, Long insClientId) {
        Policy entity = new Policy();
        entity.setId(policyId);
        entity.setInsProduct(this.initTestInsProduct(insProductId, insProductId, insProductId));
        NClientType nClientType = this.initTestClientTypePerson();
        entity.setClient(this.initTestClient(insClientId, nClientType));
        entity.setIdentityNumber("123123123");
        entity.setSum(BigDecimal.TEN);
        entity.setObjectName("Име на обекта");
        entity.setObjectDescription("Описание на обекта");
        entity.setNote("Записки на полица");
        entity.setBeginDate(DateTime.now());
        entity.setEndDate(DateTime.now().plusDays(10));
        entity.setInsObjectType(this.initNInsObjectType(insProductId));
        return entity;
    }

    public List<NInsObjectType> initTenNInsObjectType() {
        List<NInsObjectType> nInsObjectTypes = new ArrayList<>();
        for (long i = 1; i <= 10; i++) {
            nInsObjectTypes.add(this.initNInsObjectType((-1) * i));
        }

        return nInsObjectTypes;
    }

    public NInsObjectType initNInsObjectType(Long nInsObjectTypeId) {
        NInsObjectType entity = new NInsObjectType();
        entity.setId(nInsObjectTypeId);
        entity.setCode("APARTMENT");
        entity.setDescription("Апартамент");
        return entity;
    }

    public List<NInsType> initTenNInsType() {
        List<NInsType> nInsTypes = new ArrayList<>();
        for (long i = 1; i <= 10; i++) {
            nInsTypes.add(this.initTestInsType((-1) * i));
        }

        return nInsTypes;
    }

    public List<User> initTenUsers() {
        List<User> userList = new ArrayList<>();
        for (long i = 1; i <= 10; i++) {
            Long id = (-1) * i;
            User user = new User();
            user.setId(id);
            user.setUsername("Потребител " + id);
            user.setPassword("password " + id);
            List<Role> roles = this.initRoles();
            user.setRoles(roles);
            userList.add(user);
        }
        return userList;
    }
}
