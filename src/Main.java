import java.util.Collection;
import java.util.Arrays;
import java.util.List;
import java.util.stream.*;
import java.util.Random;
import java.util.ArrayList;
import java.util.Comparator;
//10_000_000
//100

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        //Найти количество несовершеннолетних (т.е. людей младше 18 лет)

        System.out.println(
                "1. "
                        + persons.stream()
                        .filter(x -> x.getAge() < 18)
                        .count()
        );

        //Получить список фамилий призывников (т.е. мужчин от 18 и до 27 лет)
        System.out.println(
                "2. "
                        + persons.stream()
                        .filter(x -> (x.getAge() >= 18
                                && x.getAge() <= 27))
                        .map(x -> x.getFamily())
                        .collect(Collectors.toList())
        );

        //Получить отсортированный по фамилии список потенциально работоспособных людей с высшим образованием в выборке (т.е. людей с высшим образованием от 18 до 60 лет для женщин и до 65 лет для мужчин)
        System.out.println(
                "3. "
                        + persons.stream()
                        .filter(x -> (x.getEducation() == Education.HIGHER
                                        && ((x.getAge() >= 18 && x.getAge() <= 60 && x.getSex() == Sex.WOMAN)
                                        || (x.getAge() >= 18 && x.getAge() <= 65 && x.getSex() == Sex.MAN))
                                )
                        )
                        .sorted(Comparator.comparing(Person::getFamily))
                        .collect(Collectors.toList())
        );
    }
}
