import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

//service:bussiness:mantıksal işlemler
//service classları repo classları ile görüşür
public class StudentService {

    Scanner input=Runner.inp;

    //repositorynin methodlarını kullanabilmek için obje oluşturalım
    private Repository repo=new StudentRepository();

    //7-a-student tablosunu oluşturma
    public void createStudentTable(){
        repo.createTable();
    }

    //8-a-verilen bilgiler ile öğrenciyi kaydetme
    public Student getStudentInfo(){
        System.out.println("AD :");
        String name=input.nextLine();
        System.out.println("SOYAD :");
        String lastname=input.nextLine();
        System.out.println("ŞEHİR :");
        String city=input.next();
        System.out.println("YAŞ :");
        int age= input.nextInt();
        input.nextLine();

        return new Student(name,lastname,city,age);//id:null
    }

    public void saveStudent(Student newStudent){
        repo.save(newStudent);
    }


    //9-a-tüm öğrencileri konsola yazdırma
    public void printAllStudents(){
        List<Student>  students=repo.findAll();
        System.out.println("====================TÜM ÖĞRENCİLER==========================");
        for (Student student:students) {
            System.out.println("id : " +student.getId()+
                    "  ad: " +student.getName()+
                    "  soyad: " +student.getLastname()+
                    " yaş : " +student.getAge()+
                    " şehir :"+student.getCity());
        }
    }

    //10-a-id si verilen öğrenciyi getirme
    public Student getStudentById(int id){
        Student student= (Student) repo.findById(id);
        return student;
    }

    //10-b-idsi verilen öğrenciyi görüntüleme
    public void printStudentById(int id){
        Student foundStudent=getStudentById(id);
        if (foundStudent==null){
            System.out.println("ID si verilen öğrenci bulunamadı.");
        }else {
            System.out.println(foundStudent);
        }
    }

    //11-a-id si verilen öğrencinin bilgilerini yeni bilgiler
    //ile değiştirme
    public void updateStudentById(int id){
        //bu id ile öğrenci var mı?
        Student foundStudent=getStudentById(id);
        if (foundStudent==null){
            System.out.println("ID si verilen öğrenci bulunamadı. ID : "+id);
        }else {//harry potter
            //girilecek yeni bilgiler nedir?
            Student newStudent=getStudentInfo();
            //var olan öğrencinin bilgilerini
            //yeni öğrenci ile değiştirelim
            foundStudent.setName(newStudent.getName());
            foundStudent.setLastname(newStudent.getLastname());
            foundStudent.setCity(newStudent.getCity());
            foundStudent.setAge(newStudent.getAge());
            //idsi aynı kalacak
            //foundstudent:name:yeni lastname:yeni city:yeni age:yeni İd:eski
            repo.update(foundStudent);
        }
    }

    //12-a-id si verilen öğrenciyi silme
    public void deleteStudentById(int id){
        repo.deleteById(id);
    }

    //13-tüm öğrencilerin ad-soyad bilgilerini rapora yazdırma
    public void generateReport(){

        List<Student> allStudents=repo.findAll();
        System.err.println("Report is loading...");
        try {
            Thread.sleep(10000);
            FileWriter writer=new FileWriter("student_report.txt");
            writer.write("*** Student Report ***\n");
            writer.write("------------------------\n");
            for (Student student:allStudents){
                writer.write("Ad : "+student.getName()+" Soyad : "+student.getLastname()+"\n");
            }
            writer.close();
            System.err.println("Report generated and printed to student_report.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }





}