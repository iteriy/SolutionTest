package ru.panmin.solution.utils;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Андрей on 25.10.2017.
 */

public class RxUtil {
    private RxUtil() {
    }

    public static void unsubscribe(Subscription subscription) {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }

    public static void unsubscribe(CompositeSubscription compositeSubscription) {
        if (compositeSubscription != null
                && compositeSubscription.hasSubscriptions()
                && !compositeSubscription.isUnsubscribed()) {
            compositeSubscription.unsubscribe();
        }
    }

}
