package TaskHub.TaskHub.converters;

public interface Converter<D, E> {
    D convertiDaEntity(E e) ;
    E convertiDaDto(D d);
}
