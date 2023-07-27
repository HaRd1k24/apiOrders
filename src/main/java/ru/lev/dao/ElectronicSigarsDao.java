package ru.lev.dao;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ru.lev.model.ElectronicSigars;
import ru.lev.util.HibernateUtil;


@Component
public class ElectronicSigarsDao {

    private static final Logger log = LoggerFactory.getLogger(ElectronicSigarsDao.class);

    /**
     *
     * @param nameSigars - название электронки
     * @param count - кол-во электронки
     *              Данный метод создает новую электронку
     */

    public void create(String nameSigars, int count) {
        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory()) {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            ElectronicSigars build = ElectronicSigars.builder()
                    .name(nameSigars)
                    .count(count).build();
            session.save(build);
            session.getTransaction().commit();
            log.info("Мы создали электронку {} и завезли: {}", build.getName(), build.getCount());
        }
    }


    /**
     *
     * @param name - название электронки
     * @param count - количество которое завозим
     *              Данный метод обновляет количество передаваемой электронки
     */
    //TODO из-за dirtySession сначала очищаю session! Есть ли более гуманый метод!?
    public void updateElectronigSigars(String name, int count){
        try(SessionFactory sessionFactory = HibernateUtil.buildSessionFactory()){
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            ElectronicSigars build = ElectronicSigars.builder()
                    .name(name)
                    .count(session.get(ElectronicSigars.class,name).getCount() + count)
                    .build();
            session.clear();
            session.update(build);
            session.getTransaction().commit();
            log.info("Обновили количество электронки: {} на: {} и теперь доступно: {}",build.getName(),count,build.getCount());
        }
    }



    /**
     * @param nameElectronicSigars - назваие электронки
     * @return возвращает количество данной электронки
     */
    public int countTaste(String nameElectronicSigars) {
        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory()) {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            return session.get(ElectronicSigars.class, nameElectronicSigars).getCount();
        }
    }

    /**
     * @param nameElectronicSigars - название электронки
     * @param howMany              - сколько хочет купить клиент
     * @return возвращает число купленных электронных при позитивном сценарии
     */
    /* dirtySession!? */
    public String buyHqd(String nameElectronicSigars, int howMany) {
        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory()) {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            ElectronicSigars electronicSigars = session.get(ElectronicSigars.class, nameElectronicSigars);
            int count = electronicSigars.getCount();
            if (count - howMany <= 0) {
                return "Покупка не может быть осуществленна, тк на базе осталось: " + (count);
            }
            electronicSigars.setCount(count -= howMany);
            session.getTransaction().commit();
            return "Вы купили: " + howMany;
        }

    }
}
