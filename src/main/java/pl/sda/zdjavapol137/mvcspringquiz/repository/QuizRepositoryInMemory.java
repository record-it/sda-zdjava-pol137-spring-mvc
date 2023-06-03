package pl.sda.zdjavapol137.mvcspringquiz.repository;

import pl.sda.zdjavapol137.mvcspringquiz.model.Quiz;

import java.util.*;

public class QuizRepositoryInMemory {
    private final Map<Long, Quiz> map;
    private int index = 0;

    public QuizRepositoryInMemory() {
        this.map = new HashMap<>();
    }

    public List<Quiz> findAll(){
        return new ArrayList<>(map.values());
    }

    public Optional<Quiz> findById(long id){
        return Optional.ofNullable(map.get(id));
    }

    public void save(Quiz quiz){
        quiz.setId(++index);
        map.put(quiz.getId(), quiz);
    }

    public void deleteById(long id){
        map.remove(id);
    }

    public void update(Quiz quiz){
        map.replace(quiz.getId(), quiz);
    }
}
