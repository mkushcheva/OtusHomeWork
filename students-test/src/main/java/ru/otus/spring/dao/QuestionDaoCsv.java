package ru.otus.spring.dao;

import org.springframework.stereotype.Component;
import ru.otus.spring.domain.Question;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
  Реализация интерфейса QuestionDao, полученного из ресурса CSV
 */
@Component
public class QuestionDaoCsv implements QuestionDao {
    @Override
    public List<Question> readQuestionsFromFileNew(File file) {
        String line = "";
        String cvsSeparator = ",";
        List<Question> questions = new ArrayList<>();
        int counter = 1;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            while ((line = br.readLine()) != null) {
                if (counter > 1) {
                    String[] str = line.split(cvsSeparator);
                    questions.add(new Question(Integer.parseInt(str[0]), str[1], str[2], str[3], str[4], Integer.parseInt(str[5])));
                }
                counter++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return questions;
    }
}
