package JBDCDemo.JDBC;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
/**
 * Hello world!
 *
 */   
public class App {    
public static void main(String[] args) {    
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    Jobs job=null;
    Criteria criteria=session.createCriteria(Jobs.class);
    criteria.add(Restrictions.like("location", "hyd"));
    criteria.setProjection(Projections.count("jobId"));
    List count=criteria.list();
    System.out.println("Count:"+count.get(0));
    List jobList = session.createQuery("FROM Jobs order by JobID").list(); 
    for (Iterator<Jobs> iterator = jobList.iterator(); iterator.hasNext();){
       job = iterator.next(); 
       System.out.print("Job Title: " + job.getJobTitle()); 
       System.out.print("  Company: " + job.getCompanyName()); 
       System.out.print("  Location: " + job.getLocation()); 
       System.out.print("Job Skill: "); 
       List skillList = session.createQuery("FROM JobSkills where JobID="+job.getJobId()).list();
       for (Iterator iterator1 = skillList.iterator(); iterator1.hasNext();){
           JobSkills skills = (JobSkills) iterator1.next(); 
           System.out.print(" " + skills.getSkill());  
        }
       System.out.println();
    }
    System.out.println("-----------------------Job Skills-------------------------");
    List skillJobList = session.createQuery("FROM JobSkills").list(); 
    for (Iterator iterator = skillJobList.iterator(); iterator.hasNext();){
       JobSkills jobSkills = (JobSkills) iterator.next(); 
       job = jobSkills.getJob();
       System.out.print("Job Title: " + job.getJobTitle()); 
       System.out.print("  Company: " + job.getCompanyName()); 
       System.out.print("  Location: " + job.getLocation()); 
       System.out.print("Job Skill: "+jobSkills.getSkill());
       System.out.println();
    }
    JobSkills jobSkills=new JobSkills(job,"TestSkill");
    session.save(jobSkills);
   
    session.getTransaction().commit();
}    
}   
