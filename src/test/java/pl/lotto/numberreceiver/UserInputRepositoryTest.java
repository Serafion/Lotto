package pl.lotto.numberreceiver;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import pl.lotto.numberreceiver.repository.UserInput;
import pl.lotto.numberreceiver.repository.UserInputRepository;

import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;

public class UserInputRepositoryTest implements UserInputRepository {


    HashMap<LocalDateTime,List<UserInput>> records = new HashMap<>();
    List<UserInput> validInputs = new ArrayList<>();

    @Override
    public UserInput save(UserInput userInput) {
        validInputs.add(userInput);
        records.put(userInput.date(), validInputs);
        return validInputs.get(validInputs.size() - 1);
    }

    @Override
    public List<UserInput> findAllByDate(LocalDateTime date) {
        return records.get(date);
    }
    @Override
    public <S extends UserInput> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<UserInput> findById(UUID uuid) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(UUID uuid) {
        return false;
    }

    @Override
    public List<UserInput> findAll() {
        return null;
    }

    @Override
    public Iterable<UserInput> findAllById(Iterable<UUID> uuids) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(UUID uuid) {

    }

    @Override
    public void delete(UserInput entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends UUID> uuids) {

    }

    @Override
    public void deleteAll(Iterable<? extends UserInput> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<UserInput> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<UserInput> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public <S extends UserInput> S insert(S entity) {
        return null;
    }

    @Override
    public <S extends UserInput> List<S> insert(Iterable<S> entities) {
        return null;
    }

    @Override
    public <S extends UserInput> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends UserInput> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends UserInput> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends UserInput> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends UserInput> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends UserInput> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends UserInput, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }
}
