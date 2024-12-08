import java.util.List;

//course,student,instrutor ıcın
//data tıpınden bagımsız vrud ıslemlerı lısteleyen bır ınterfaceye ıhtıyacım var
public interface Repository<S, U> {

    void createTable();

    void save(S entity);

    List<S> findAll();

    void update(S entity);

    void deleteById(U id);

    S findById(U id);

}
