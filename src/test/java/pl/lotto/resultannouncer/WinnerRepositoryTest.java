package pl.lotto.resultannouncer;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import pl.lotto.resultannouncer.repository.ResultTicket;
import pl.lotto.resultannouncer.repository.WinnerRepository;

import java.util.*;
import java.util.function.Function;

public class WinnerRepositoryTest implements WinnerRepository {


    Map<UUID, ResultTicket> map = new HashMap<>();
    @Override
    public <S extends ResultTicket> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public List<ResultTicket> findAll() {
        return null;
    }

    @Override
    public List<ResultTicket> findAll(Sort sort) {
        return null;
    }

    @Override
    public <S extends ResultTicket> S insert(S entity) {
        return null;
    }

    @Override
    public <S extends ResultTicket> List<S> insert(Iterable<S> entities) {
        return null;
    }

    @Override
    public <S extends ResultTicket> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends ResultTicket> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public Page<ResultTicket> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public <S extends ResultTicket> S save(S entity) {
        map.put(entity.getUuid(), new ResultTicket(entity.getUuid(), entity.getWonNumbers(), entity.getDate()));
        return (S) new ResultTicket(entity.getUuid(), entity.getWonNumbers(), entity.getDate());
    }

    @Override
    public Optional<ResultTicket> findById(UUID uuid) {
        return Optional.of(map.get(uuid));
    }

    @Override
    public boolean existsById(UUID uuid) {
        return map.containsKey(uuid);
    }

    @Override
    public Iterable<ResultTicket> findAllById(Iterable<UUID> uuids) {
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
    public void delete(ResultTicket entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends UUID> uuids) {

    }

    @Override
    public void deleteAll(Iterable<? extends ResultTicket> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends ResultTicket> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends ResultTicket> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends ResultTicket> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends ResultTicket> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends ResultTicket, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }
}
