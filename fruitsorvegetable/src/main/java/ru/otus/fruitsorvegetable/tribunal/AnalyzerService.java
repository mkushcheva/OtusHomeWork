package ru.otus.fruitsorvegetable.tribunal;

import org.springframework.stereotype.Service;
import ru.otus.fruitsorvegetable.domain.Answer;
import ru.otus.fruitsorvegetable.domain.Plant;

import java.util.HashSet;

@Service
public class AnalyzerService {

    public Answer process(Plant plant) throws Exception {
        System.out.println("Идет проверка  " + plant.getPlant());
        Thread.sleep(3000);
        Answer answer = check(plant);

        System.out.println("Проверка " + plant.getPlant() + " завершена");

        return answer;
    }

    private Answer check(Plant plant) {

        HashSet<String> fruitSet = new HashSet<>();
        fruitSet.add("яблоко");
        fruitSet.add("апельсин");
        fruitSet.add("мандарин");
        fruitSet.add("клубника");

        if (fruitSet.contains(plant.getPlant()))
            return new Answer("Фрукты");
        else return new Answer("Овощи");
    }
}
