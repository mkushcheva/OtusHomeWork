package ru.otus.spring.dao;

import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.ParseInt;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.prefs.CsvPreference;
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
public class QuestionDaoCsv implements QuestionDao {
    // указываем как будем мапить
    private static final String[] MAPPING = new String[]{
            "number",
            "question",
            "answer1",
            "answer2",
            "answer3"
    };

    /**
     * Задаем обработчики ячеек
     */
    private static CellProcessor[] getProcessors() {
        return new CellProcessor[]{
                new Optional(new ParseInt()),
                new Optional(),
                new Optional(),
                new Optional(),
                new Optional()
        };
    }

    public List<Question> readQuestionsFromFile(File file) throws IOException {
        List<Question> questions = new ArrayList<>();

        ICsvBeanReader csvBeanReader = new CsvBeanReader(new FileReader(file), CsvPreference.STANDARD_PREFERENCE);
        csvBeanReader.getHeader(true);
        // получаем обработчики
        CellProcessor[] procs = getProcessors();
        Question question;
        // обходим весь csv файлик до конца
        while ((question = csvBeanReader.read(Question.class, MAPPING, procs)) != null) {
            questions.add(question);
        }
        csvBeanReader.close();
        return questions;
    }

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
                    questions.add(new Question(Integer.parseInt(str[0]), str[1], str[2], str[3], str[4]));
                }
                counter++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return questions;
    }
}
