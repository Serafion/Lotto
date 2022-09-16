package pl.lotto.resultchecker;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import pl.lotto.resultchecker.checkerdto.CheckerDto;
import pl.lotto.resultchecker.repository.ResultCheckerRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class InputRepositoryTest implements ResultCheckerRepository {


    @Override
    public boolean existsById(LocalDateTime dateTime) {
        return false;
    }


    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(LocalDateTime dateTime) {

    }


    @Override
    public void deleteAllById(Iterable<? extends LocalDateTime> localDateTimes) {

    }

    @Override
    public <S extends CheckerDto> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public List<CheckerDto> findAll() {
        return null;
    }

    @Override
    public List<CheckerDto> findAll(Sort sort) {
        return null;
    }

    @Override
    public <S extends CheckerDto> S insert(S entity) {
        return null;
    }

    @Override
    public <S extends CheckerDto> List<S> insert(Iterable<S> entities) {
        return null;
    }

    @Override
    public <S extends CheckerDto> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends CheckerDto> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public Page<CheckerDto> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public <S extends CheckerDto> S save(S entity) {
        return null;
    }

    @Override
    public Optional<CheckerDto> findById(LocalDateTime dateTime) {
        return Optional.empty();
    }

    @Override
    public Iterable<CheckerDto> findAllById(Iterable<LocalDateTime> localDateTimes) {
        return null;
    }

    @Override
    public void delete(CheckerDto entity) {

    }

    @Override
    public void deleteAll(Iterable<? extends CheckerDto> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends CheckerDto> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends CheckerDto> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends CheckerDto> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends CheckerDto> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends CheckerDto, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }
}
