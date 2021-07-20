package ru.otus.spring.dao;

import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.ParseInt;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.prefs.CsvPreference;
import ru.otus.spring.domain.Question;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
  Реализация интерфейса QuestionDao, полученного из ресурса CSV
 */
public class QuestionDaoCsv implements QuestionDao{
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

    @Override
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

}
