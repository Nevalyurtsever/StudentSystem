import java.util.Scanner;

//uygulamayı başlatan ve (controller:istemci ile iletişime geçilecek)
//controller ile sadece service classları görüşür
public class Runner {

    public static Scanner inp=new Scanner(System.in);

    public static void main(String[] args) {
        start();
    }

    private static void start() {

        StudentService service=new StudentService();
        //7-c:uygulama başladığında tablo oluşturulsun.
        service.createStudentTable();

        int select;
        int id;
        do {
            System.out.println("========================================================");
            System.out.println("Öğrenci Yönetim Paneli");
            System.out.println("1-Öğrenci Kaydetme");
            System.out.println("2-Tüm Öğrencileri Görüntüleme");
            System.out.println("3-Öğrenciyi Güncelleme");
            System.out.println("4-Öğrenciyi Silme");
            System.out.println("5-Tek Bir Öğrenciyi Görüntüleme");
            System.out.println("6-Tüm Öğrencilerin AD-SOYAD bilgilerini rapora yazdırma");
            System.out.println("0-ÇIKIŞ");
            System.out.println("İşlem Seçiniz : ");
            select= inp.nextInt();
            inp.nextLine();

            switch (select){
                case 1:
                    //bilgileri verilen öğrenciyi kaydetme
                    Student newStudent=service.getStudentInfo();
                    new Thread(()->{
                        service.saveStudent(newStudent);
                    }).start();
                    break;
                case 2:
                    //öğrencileri konsola yazdırma
                    service.printAllStudents();
                    break;
                case 3:
                    id=getIdInfo();
                    //id si verilen öğrenciyi güncelleme
                    service.updateStudentById(id);
                    break;
                case 4:
                    id=getIdInfo();
                    //id si verilen öğrenciyi silme
                    service.deleteStudentById(id);
                    break;
                case 5:
                    id=getIdInfo();
                    //id si verilen öğrenciyi görüntüleme
                    service.printStudentById(id);
                    break;
                case 6:
                    new Thread(()->{
                        service.generateReport();
                    }).start();
                    break;
                case 0:
                    System.out.println("İyi Günler...");
                    break;
                default:
                    System.out.println("Hatalı giriş!!!");
                    break;
            }
        }while (select!=0);



    }

    private static int getIdInfo(){
        System.out.println("Öğrenci id : ");
        int id=inp.nextInt();
        inp.nextLine();
        return id;
    }

}