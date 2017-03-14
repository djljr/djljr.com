(ns djljr-com.views.common_test
  (:require [djljr-com.views.common :refer :all]
            [midje.sweet :refer :all]
            [clj-time.core :as t]
            [clj-time.coerce :as ct]))

(fact "date formatting"
      (date->date-str (ct/to-date (t/date-time 2017 03 14))) => "2017-03-14"
      (date->time-ago (t/minus (t/now) (t/days 1))) => "1 day ago"
      (date->time-ago (t/minus (t/now) (t/days 2))) => "2 days ago")
