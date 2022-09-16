package pl.lotto.resultchecker;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import pl.lotto.resultchecker.repository.CheckerRepoEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

public class DrawDateRepository implements pl.lotto.resultchecker.repository.DrawDateRepository {

    @Override
    public <S extends CheckerRepoEntity> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public List<CheckerRepoEntity> findAll() {
        return null;
    }

    @Override
    public List<CheckerRepoEntity> findAll(Sort sort) {
        return null;
    }

    @Override
    public <S extends CheckerRepoEntity> S insert(S entity) {
        return null;
    }

    @Override
    public <S extends CheckerRepoEntity> List<S> insert(Iterable<S> entities) {
        return null;
    }

    @Override
    public <S extends CheckerRepoEntity> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends CheckerRepoEntity> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public Page<CheckerRepoEntity> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public <S extends CheckerRepoEntity> S save(S entity) {
        return null;
    }

    @Override
    public Optional<CheckerRepoEntity> findById(UUID uuid) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(UUID uuid) {
        return false;
    }

    @Override
    public Iterable<CheckerRepoEntity> findAllById(Iterable<UUID> uuids) {
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
    public void delete(CheckerRepoEntity entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends UUID> uuids) {

    }

    @Override
    public void deleteAll(Iterable<? extends CheckerRepoEntity> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends CheckerRepoEntity> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends CheckerRepoEntity> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends CheckerRepoEntity> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends CheckerRepoEntity> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends CheckerRepoEntity, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }
}
