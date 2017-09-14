'use strict';
P.when('A').register('string-util', function(A) {
    var getJQueryIdSelector = function(id) {
        return "#" + id;
    };

    var isBlank = function(str) {
        return (!str || /^\s*$/.test(str)); //return (!str || 0 === str.length);
    };

    var startsWith = function(text, needle) {
        if (text && needle && text.substring(0, needle.length) === needle) {
            return true;
        }

        return false;
    };

    var endsWith = function(text, needle) {
        if (text && needle && text.substring(text.length - needle.length, text.length) === needle) {
            return true;
        }

        return false;
    };

    var parseDate = function(date) {
        if (date instanceof Date) {
            return
        }
    };

    return {
        isBlank : isBlank,
        startsWith : startsWith,
        endsWith : endsWith,
        getJQueryIdSelector : getJQueryIdSelector
    };
});

P.when('A').register('date-util', function(A) {
    var compare = function(dateFirst, dateSecond) {
        var difference = diff(dateFirst, dateSecond)
        return difference === 0 ? 0 : (difference < 0 ? -1 : 1);
    };

    var diff = function(dateFirst, dateSecond) {
        return dateFirst.getTime() - dateSecond.getTime();
    };

    var min = function(dateFirst, dateSecond) {
        return diff(dateFirst, dateSecond) <= 0 ? dateFirst : dateSecond;
    };

    var max = function(dateFirst, dateSecond) {
        return diff(dateFirst, dateSecond) <= 0 ? dateSecond : dateFirst;
    };

    Date.prototype.diff = function (another) {
            return diff(this, another);
        };

    Date.prototype.min = function (another) {
        return min(this, another);
    };

    Date.prototype.max = function (another) {
        return max(this, another);
    };

    Date.prototype.compareTo = function (another) {
        return compare(this, another);
    };

    return {
        min : min,
        max : max,
        diff : diff,
        compare : compare
    };
});

P.when('A').register('date-parser', function(A) {
    /*
    * Accepts a date, a mask, or a date and a mask.
    * Returns a formatted version of the given date.
    * The date defaults to the current date/time.
    * The mask defaults to dateFormat.masks.default.
    */
    var dateFormat = function () {
        var token = /d{1,4}|m{1,4}|yy(?:yy)?|([HhMsTt])\1?|[LloSZ]|"[^"]*"|'[^']*'/g,
            timezone = /\b(?:[PMCEA][SDP]T|(?:Pacific|Mountain|Central|Eastern|Atlantic) (?:Standard|Daylight|Prevailing) Time|(?:GMT|UTC)(?:[-+]\d{4})?)\b/g,
            timezoneClip = /[^-+\dA-Z]/g,
            pad = function (val, len) {
                val = String(val);
                len = len || 2;
                while (val.length < len) val = "0" + val;
                return val;
            };

        // Regexes and supporting functions are cached through closure
        return function (date, mask, utc) {
            var dF = dateFormat;

            // You can't provide utc if you skip other args (use the "UTC:" mask prefix)
            if (arguments.length == 1 && Object.prototype.toString.call(date) == "[object String]" && !/\d/.test(date)) {
                mask = date;
                date = undefined;
            }

            // Passing date through Date applies Date.parse, if necessary
            date = date ? new Date(date) : new Date;
            if (isNaN(date)) throw SyntaxError("invalid date");

            mask = String(dF.masks[mask] || mask || dF.masks["default"]);

            // Allow setting the utc argument via the mask
            if (mask.slice(0, 4) == "UTC:") {
                mask = mask.slice(4);
                utc = true;
            }

            var _ = utc ? "getUTC" : "get",
                d = date[_ + "Date"](),
                D = date[_ + "Day"](),
                m = date[_ + "Month"](),
                y = date[_ + "FullYear"](),
                H = date[_ + "Hours"](),
                M = date[_ + "Minutes"](),
                s = date[_ + "Seconds"](),
                L = date[_ + "Milliseconds"](),
                o = utc ? 0 : date.getTimezoneOffset(),
                flags = {
                    d:    d,
                    dd:   pad(d),
                    ddd:  dF.i18n.dayNames[D],
                    dddd: dF.i18n.dayNames[D + 7],
                    m:    m + 1,
                    mm:   pad(m + 1),
                    mmm:  dF.i18n.monthNames[m],
                    mmmm: dF.i18n.monthNames[m + 12],
                    yy:   String(y).slice(2),
                    yyyy: y,
                    h:    H % 12 || 12,
                    hh:   pad(H % 12 || 12),
                    H:    H,
                    HH:   pad(H),
                    M:    M,
                    MM:   pad(M),
                    s:    s,
                    ss:   pad(s),
                    l:    pad(L, 3),
                    L:    pad(L > 99 ? Math.round(L / 10) : L),
                    t:    H < 12 ? "a"  : "p",
                    tt:   H < 12 ? "am" : "pm",
                    T:    H < 12 ? "A"  : "P",
                    TT:   H < 12 ? "AM" : "PM",
                    Z:    utc ? "UTC" : (String(date).match(timezone) || [""]).pop().replace(timezoneClip, ""),
                    o:    (o > 0 ? "-" : "+") + pad(Math.floor(Math.abs(o) / 60) * 100 + Math.abs(o) % 60, 4),
                    S:    ["th", "st", "nd", "rd"][d % 10 > 3 ? 0 : (d % 100 - d % 10 != 10) * d % 10]
                };

            return mask.replace(token, function ($0) {
                return $0 in flags ? flags[$0] : $0.slice(1, $0.length - 1);
            });
        };
    }();

    // Some common format strings
    dateFormat.masks = {
        "default":      "ddd mmm dd yyyy HH:MM:ss",
        shortDate:      "m/d/yy",
        mediumDate:     "mmm d, yyyy",
        longDate:       "mmmm d, yyyy",
        fullDate:       "dddd, mmmm d, yyyy",
        shortTime:      "h:MM TT",
        mediumTime:     "h:MM:ss TT",
        longTime:       "h:MM:ss TT Z",
        isoDate:        "yyyy-mm-dd",
        isoTime:        "HH:MM:ss",
        isoDateTime:    "yyyy-mm-dd'T'HH:MM:ss",
        isoUtcDateTime: "UTC:yyyy-mm-dd'T'HH:MM:ss'Z'"
    };

    // Internationalization strings
    dateFormat.i18n = {
        dayNames: [
            "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat",
            "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"
        ],
        monthNames: [
            "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec",
            "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"
        ]
    };

    // For convenience...
    Date.prototype.format = function (mask, utc) {
        return dateFormat(this, mask, utc);
    };
});

P.when('A', 'a-dropdown').register('dropdown-util', function(A, dropdown) {
    var setUpDropdown = function($select, selectedIndex, values, texts) {
        if (!values) return;

        texts = texts || [];
        if (texts.length == 0) {
            texts = A.extend(values, texts);
        }

        $select.removeOptions();
        for (var i = 0, l = Math.min(values.length, texts.length); i < l; i++) {
            $select.appendOption({text: texts[i], value : values[i]});
        }

        selectedIndex = Math.min(Math.max(selectedIndex, 0), l - 1);
        $select.setValue(values[selectedIndex]);
    };

    return {
        setUpDropdown : setUpDropdown
    };
});


P.when('A').register('audit-trail-util', function(A) {
    var validateQueryData = function(queryData) {
        return;
    };

    var queryConstant = {
        queryButtonId : "queryButton"
    };

    var actorConstant = {
        actorId : "actor",
        actorCategoryId : "actorCategory"
    };

    var targetConstant = {
        targetId : "target",
        targetCategoryId : "targetCategory"
    };

    var dropdownConstant = {
        timePeriodSelector : "timePeriodSelector",
        actorCategorySelector : "actorCategorySelector",
        targetCategorySelector : "targetCategorySelector"
    };

    var calendarConstant = {
        atdFromId : "calendarFrom",
        atdToId : "calendarTo",
        atdFromInputId : "calendarFromInput",
        atdToInputId : "calendarToInput",
        atdFromContainerId : "calendarFromContainer",
        atdToContainerId : "calendarToContainer"
    };


    var timePeriodOptions = {
        LastThreeHours : 'Last 3 hours',
        LastOneDay : 'Last 1 day',
        LastThreeDays : 'Last 3 days',
        LastOneWeek : 'Last 1 week',
        LastOneMonth : 'Last 1 month',
        LastThreeMonths : 'Last 3 months',
        LastOneYear : 'Last 1 year',
        All : 'All'
    };

    var queryAjaxConstant = {
         Method : 'POST',
         URL : '/auditTrail/query',
         ContentType : 'application/json'
    };


    var maxTimeOfToday = function() {
        var now = new Date();
        now.setHours(23);
        now.setMinutes(59);
        now.setSeconds(59);
        now.setMilliseconds(999);
        return now;
    };

    return {
        maxTimeOfToday : maxTimeOfToday,
        queryConstant : queryConstant,
        actorConstant : actorConstant,
        targetConstant : targetConstant,
        dropdownConstant : dropdownConstant,
        calendarConstant : calendarConstant,
        timePeriodOptions : timePeriodOptions,
        validateQueryData : validateQueryData,
        queryAjaxConstant : queryAjaxConstant
    };

});