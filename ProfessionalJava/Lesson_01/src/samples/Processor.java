package samples;

/**
 * Processor
 *
 * @author anton
 * @since 19/08/19
 */
public interface Processor<T> {
    int process(T v1, T v2);
}
