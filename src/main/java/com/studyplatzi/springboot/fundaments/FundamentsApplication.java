package com.studyplatzi.springboot.fundaments;

import com.studyplatzi.springboot.fundaments.been.MyBean;
import com.studyplatzi.springboot.fundaments.been.MyBeanWithDependency;
import com.studyplatzi.springboot.fundaments.been.MyBeanWithProperties;
import com.studyplatzi.springboot.fundaments.component.ComponentDependency;
import com.studyplatzi.springboot.fundaments.entity.User;
import com.studyplatzi.springboot.fundaments.pojo.UserPojo;
import com.studyplatzi.springboot.fundaments.repository.UserRepository;
import com.studyplatzi.springboot.fundaments.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class FundamentsApplication implements CommandLineRunner {

    private final Log LOGGER = LogFactory.getLog(FundamentsApplication.class);

    //------
    //Dependency injection
    private ComponentDependency componentDependency;
    private MyBean myBean;
    private MyBeanWithDependency myBeanWithDependency;
    private MyBeanWithProperties myBeanWithProperties;
    private UserPojo userPojo;
    private UserRepository userRepository;
    private UserService userService;

    @Autowired  //This annotation is not obligatory
    public FundamentsApplication(@Qualifier("componentTwoImplement") ComponentDependency componentDependency
            , MyBean myBean
            , MyBeanWithDependency myBeanWithDependency
            , MyBeanWithProperties myBeanWithProperties
            , UserPojo userPojo
            , UserRepository userRepository
            , UserService userService) {
        //if you have two class with the same implementacion about interface, you must to use @Qualifier or @Primary
        this.componentDependency = componentDependency;
        this.myBean = myBean;
        this.myBeanWithDependency = myBeanWithDependency;
        this.myBeanWithProperties = myBeanWithProperties;
        this.userPojo = userPojo;
        this.userRepository = userRepository;
        this.userService = userService;
    }
    //------


    public static void main(String[] args) {
        SpringApplication.run(FundamentsApplication.class, args);
    }

    //Uso de la dependencia
    @Override
    public void run(String... args) throws Exception {
        //ejemplosAnteriores();
        saveUsersInDataBase();
        getInformationJpqlUser();
        saveWithErrorTransacctional();

    }

    private void saveWithErrorTransacctional(){
        User test1 = new User("TestTransactional1", "TestTransactional1@domain.com", LocalDate.now());
        User test2 = new User("TestTransactional2", "TestTransactional2@domain.com", LocalDate.now());
        User test3 = new User("TestTransactional3", "TestTransactional3@domain.com", LocalDate.now());
        User test4 = new User("TestTransactional4", "TestTransactional4@domain.com", LocalDate.now());

        List<User> users = Arrays.asList(test1, test2,test3,test4);

        try{
            userService.saveTransactional(users);
        }catch (Exception e){
            LOGGER.error("Esta es una esxception dentro del metodo transaccional" + e);
        }


        userService.getAllUsers()
                .stream()
                .forEach(user->LOGGER.info("Este es el usuario dentro del metodo transacional " + user));
    }

    private void getInformationJpqlUser() {
        LOGGER.info("User with method findByUserEmail" + userRepository.findByUserEmail("carlos@domain.com")
                .orElseThrow(() -> new RuntimeException("No se encontro el usuario")));

        userRepository.findAndSort("Ma", Sort.by("id").descending())
                .stream()
                .forEach(user -> LOGGER.info("User with method sort " + user));

        //Llamado del query method
        userRepository.findByName("John")
                .stream()
                .forEach(user -> LOGGER.info("Usuario con query method " + user));

        LOGGER.info("Usuario con Query method" + userRepository.findByEmailAndName("marisol@domain.com", "Marisol")
                .orElseThrow(() -> new RuntimeException("usuario no encontrado")));

        userRepository.findByNameLike("%Ma%")
                .stream()
                .forEach(user -> LOGGER.info("Usuario findbyNamelike " + user));

        userRepository.findByNameOrEmail(null, "marisol@domain.com")
                .stream()
                .forEach(user -> LOGGER.info("Usuario findByNameLike " + user));

        userRepository.findByBirthDateBetween(LocalDate.of(2021, 3, 1), LocalDate.of(2021, 6, 30))
                .stream()
                .forEach(user -> LOGGER.info("Usuario con intervalo de fechas: " + user));

        userRepository.findByNameLikeOrderByIdDesc("%Ma%")
                .stream()
                .forEach(user -> LOGGER.info("Usuario encontrado con like y ordenado " + user));

        userRepository.findByNameContainingOrderByIdDesc("%Ma%")
                .stream()
                .forEach(user -> LOGGER.info("Usuario encontrado con Containing y ordenado " + user));

        LOGGER.info("El usuario a partir del named parameter es: " +
                userRepository.getAllByBirthDateAndEmail(
                                LocalDate.of(2021, 2, 27),
                                "luis@domain.com")
                        .orElseThrow(() -> new RuntimeException("No se encontro el usuario a partir del named parameter")));
    }

    private void saveUsersInDataBase() {
        User user1 = new User("John", "john@domain.com", LocalDate.of(2021, 3, 13));
        User user2 = new User("Marco", "marco@domain.com", LocalDate.of(2021, 12, 8));
        User user3 = new User("Daniela", "daniela@domain.com", LocalDate.of(2021, 9, 8));
        User user4 = new User("Marisol", "marisol@domain.com", LocalDate.of(2021, 6, 18));
        User user5 = new User("Karen", "karen@domain.com", LocalDate.of(2021, 1, 1));
        User user6 = new User("Carlos", "carlos@domain.com", LocalDate.of(2021, 7, 7));
        User user7 = new User("Enrique", "enrique@domain.com", LocalDate.of(2021, 11, 12));
        User user8 = new User("Luis", "luis@domain.com", LocalDate.of(2021, 2, 27));
        User user9 = new User("Paola", "paola@domain.com", LocalDate.of(2021, 4, 10));

        List<User> userList = Arrays.asList(user1, user2, user3, user4, user5, user6, user7, user8, user9);

        //userList.stream().forEach(userRepository::save);
        userList.forEach(userRepository::save);
    }

    private void ejemplosAnteriores() {
        componentDependency.saludar();
        myBean.print();
        myBeanWithDependency.printWithDependency();
        System.out.println(myBeanWithProperties.function());
        System.out.println(userPojo.getEmail() + " - " + userPojo.getPassword());
        try {
            //error
            int value = 10 / 0;
            LOGGER.debug("Mi valor :" + value);
        } catch (Exception e) {
            LOGGER.error("Esto es un error del aplicativo al dividor por cero " + e.getMessage());
        }
    }
}
