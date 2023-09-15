package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }


   @Override
   @SuppressWarnings("unchecked")
   public User getUserByCarParams(String model, int series) {

      //TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("SELECT User FROM User u JOIN FETCH u.usersCar c WHERE c.model = :model AND c.series = :series");
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User user JOIN FETCH user.usersCar car where car.model = :model AND car.series = :series");
      query.setParameter("model", model);
      query.setParameter("series", series);
      return query.getSingleResult();
   }

   /*@Override
   public User getUserByCar(Car car) {
      //try(Session session = HibernateUtil.getSessionFactory().openSession()) {
      User user = null;
      try (Session session = sessionFactory.getCurrentSession()) {

         String HQL = "FROM User as user LEFT JOIN FETCH user.usersCar WHERE user.id = :id";
         user = session.createQuery(HQL, User.class).setParameter("id", 1).uniqueResult();
         System.out.println(user);
         System.out.println(user.getCar());
      } catch (HibernateException e) {
         e.printStackTrace();
      }
      return user;
   }*/

}
