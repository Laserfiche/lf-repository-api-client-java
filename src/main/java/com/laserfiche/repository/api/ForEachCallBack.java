package com.laserfiche.repository.api;

/**
 * Given the result of an API call, returns a boolean to indicate whether the API method should continue retrieving more
 * data.
 *
 * @param <R> A data structure that holds returned data.
 */
@FunctionalInterface
public interface ForEachCallBack<R> {
    boolean apply(R result);
}
