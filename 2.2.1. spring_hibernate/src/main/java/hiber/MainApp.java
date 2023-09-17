package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context =
              new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      userService.add(new User("User1", "Lastname1", "user1@mail.ru", new Car("Ford", 1234)));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru", new Car("Cadillac", 2345)));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru", new Car("Dodge", 3456)));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru", new Car("Chevrolet", 1122)));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("User's car model is " + user.getCar().getModel());
         System.out.println("Users car series = " + user.getCar().getSeries());
         System.out.println();
      }

      //Значения для проверочного запроса
      String testModel = "Chevrolet";
      int testSeries = 1122;
      User user = userService.getUserByCarParams("Chevrolet", 1122);
      System.out.println("-------------------------------RESULT-------------------------------");
      System.out.println("User, у которого модель авто " + testModel + ", а значение Series = " + testSeries + ":");
      System.out.println(user);
      System.out.println("-------------------------------RESULT-------------------------------");

      context.close();
   }
}