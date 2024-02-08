//#####################################################################################################################
//#####################################################################################################################
//###############################             Календарь - блок алгоритмов               ###############################
//#####################################################################################################################
//####    Автор: Кулишов Илья Вячеславович    #########################################################################
//####    Версия: v.0.0.0.7                   #########################################################################
//####    Дата: 04.02.2024                    #########################################################################
//#####################################################################################################################
//#####################################################################################################################




//#####################################################################################################################
//#####################################################################################################################
//###############################              Блок вычислительных функций              ###############################
//#####################################################################################################################
//#####################################################################################################################

//=====================================================================================
//Функции определения текущего дня недели
//Входные данные:
//                      year:Int    - Год
//                      month:Int   - Месяц
//                      day:Int     - День
//Возращаемое значение:
//                      plus:Int    - Смещение дня недели от понедельника
//=====================================================================================
fun showWeekDay(year:Int, month:Int, day:Int): Int {
    var plus = 0
    var year1 = 2000
    var month1 = 1
    var day1 = 1
    var ret = ""

    val mmonth = IntArray(13)
    mmonth[1] = 31
    mmonth[2] = 28
    mmonth[3] = 31
    mmonth[4] = 30
    mmonth[5] = 31
    mmonth[6] = 30
    mmonth[7] = 31
    mmonth[8] = 31
    mmonth[9] = 30
    mmonth[10] = 31
    mmonth[11] = 30
    mmonth[12] = 31

    if (year1 < year || (year1 == year && month1 < month) || (year1 == year && month1 == month && day1 <= day)) {
        while (year1 != year) {
            if (year1 % 4 == 0) plus += 2
            else plus += 1
            year1++
        }
        while (month1 != month) {
            if (month1 == 2 && year1 % 4 == 0){ plus += 29}
            else {plus += mmonth[month1]}
            month1++
        }
        plus += (day - day1)
        plus = plus%7
        //ret = dayWeek[plus % 7]!!
    } else {
        var year2 = year
        var month2 = month
        var day2 = day

        if (year2 % 4 == 0) mmonth[2] = 29
        else mmonth[2] = 28

        plus += mmonth[month2] - day2
        if (month2 == 12) month2 = 1
        else {
            month2++
            while (month2 != 12) {
                plus += mmonth[month2] % 7
                month2++
            }
            mmonth[month2] % 7
        }

        while (year2 != year1) {
            if (year2 % 4 == 0) plus += 2
            else plus += 1
            year2++
        }
        plus = plus % 7
        //ret = dayWeek[(7 - plus - 1)]!!
    }
    plus=plus-2
    if(plus==-2){
        plus=5
    }
    if(plus==-1){
        plus=6
    }

    return (plus)
}
//=====================================================================================
//=====================================================================================

//=====================================================================================
//Функции определения события на принадлежность часу
//Входные данные:
//                      date:DateM  - Текущая дата
//                      hour: Int   - Текущий час
//                      event:Event - Проверяемое событие
//Возращаемое значение:
//                      ret:Boolean - да/нет
//=====================================================================================
fun eventDetect(date: DateM, hour: Int, event:Event):Boolean{
    var _dateStart = event.dayStart
    var ret=false
    val mmonth = IntArray(13)
    mmonth[1] = 31
    mmonth[2] = 28
    mmonth[3] = 31
    mmonth[4] = 30
    mmonth[5] = 31
    mmonth[6] = 30
    mmonth[7] = 31
    mmonth[8] = 31
    mmonth[9] = 30
    mmonth[10] = 31
    mmonth[11] = 30
    mmonth[12] = 31
    if(_dateStart.year%4==0){
        mmonth[2]=29
    }
    else{
        mmonth[2]=28
    }
    if(date.year==_dateStart.year&&date.month==_dateStart.month&&date.day==_dateStart.day){
        ret=detectTime(hour,event)
    }
    while(!(_dateStart.year==event.dayFinish.year&&_dateStart.month==event.dayFinish.month&&_dateStart.day==event.dayFinish.day)){
        if(_dateStart.day==mmonth[_dateStart.month]){
            if(_dateStart.month==12){
                _dateStart.year=_dateStart.year+1
                _dateStart.month=1
                if(_dateStart.year%4==0){
                    mmonth[2]=29
                }
                else{
                    mmonth[2]=28
                }
            }
            else{
                _dateStart.month=_dateStart.month+1
            }
            _dateStart.day=1
        }
        else{
            _dateStart.day=_dateStart.day+1
        }
        if(date.year==_dateStart.year&&date.month==_dateStart.month&&date.day==_dateStart.day){
            ret = detectTime(hour, event)
        }
    }
    if(event.povt!=0){
        if(eventPovtDetect(date,event)){
            ret=detectTime(hour, event)
        }
    }
    return ret
}
//=====================================================================================

//=====================================================================================
//Функции определения события на принадлежность к дню
//Входные данные:
//                      date:DateM  - Текущая дата
//                      event:Event - Проверяемое событие
//Возращаемое значение:
//                      ret:Boolean - да/нет
//=====================================================================================
fun eventDetectDate(date: DateM,  event:Event):Boolean{
    var _dateStart = event.dayStart
    var ret=false
    val mmonth = IntArray(13)
    mmonth[1] = 31
    mmonth[2] = 28
    mmonth[3] = 31
    mmonth[4] = 30
    mmonth[5] = 31
    mmonth[6] = 30
    mmonth[7] = 31
    mmonth[8] = 31
    mmonth[9] = 30
    mmonth[10] = 31
    mmonth[11] = 30
    mmonth[12] = 31
    if(_dateStart.year%4==0){
        mmonth[2]=29
    }
    else{
        mmonth[2]=28
    }
    if(date.year==_dateStart.year&&date.month==_dateStart.month&&date.day==_dateStart.day){
        ret=true
    }
    while(!(_dateStart.year==event.dayFinish.year&&_dateStart.month==event.dayFinish.month&&_dateStart.day==event.dayFinish.day)){
        if(_dateStart.day==mmonth[_dateStart.month]){
            if(_dateStart.month==12){
                _dateStart.year=_dateStart.year+1
                _dateStart.month=1
                if(_dateStart.year%4==0){
                    mmonth[2]=29
                }
                else{
                    mmonth[2]=28
                }
            }
            else{
                _dateStart.month=_dateStart.month+1
            }
            _dateStart.day=1
        }
        else{
            _dateStart.day=_dateStart.day+1
        }
        if(date.year==_dateStart.year&&date.month==_dateStart.month&&date.day==_dateStart.day){
            ret=true
        }
    }
    if(event.povt!=0){
        if(eventPovtDetect(date, event)) ret=true
    }
    return ret
}
//=====================================================================================


//=====================================================================================
//Функция сравнения дат
//Входные данные:
//                      day1:DateM  - первая дата
//                      day2:DateM  - вторая дата
//Возращаемое значение:
//                      ret:Boolean - да/нет
//=====================================================================================
fun dateCompare(day1:DateM, day2:DateM):Boolean{
    var ret=false
    if (day1.day==day2.day&&day1.month==day2.month&&day1.year==day2.year){
        ret=true
    }
    return ret
}
//=====================================================================================
//=====================================================================================
//Функция сравнения дат(на больше или равно или нет)
//Входные данные:
//                      day1:DateM  - первая дата
//                      day2:DateM  - вторая дата
//Возращаемое значение:
//                      ret:Boolean - да/нет
//=====================================================================================
fun dateCompare1(day1:DateM, day2:DateM):Boolean{
    var ret = false
    if(day1.year>day2.year){
        ret=true
    }
    else{
        if(day1.year==day2.year&&day1.month>day2.month){
            ret=true
        }
        else{
            if(day1.year==day2.year&&day1.month==day2.month&&day1.day>=day2.year){
                ret = true
            }
        }
    }
    return ret
}
//=====================================================================================

//=====================================================================================
//=====================================================================================
//Функция проверки событий с повторением на совпадения дня
//Входные данные:
//                      day1:DateM  - текущий день
//                      event:Event - событие
//Возращаемое значение:
//                      ret:Boolean - да/нет
//=====================================================================================
fun eventPovtDetect(day1: DateM, event: Event):Boolean{
    var ret = false
    if(event.povt==1){
        if(showWeekDay(event.dayStart.year,event.dayStart.month,event.dayStart.day)==
            showWeekDay(day1.year,day1.month,day1.day)){
            ret=true
        }
    }
    if(event.povt==2){
        if(day1.day==event.dayStart.day){
            ret=true
        }
    }
    if(event.povt==3){
        if(day1.day==event.dayStart.day&&day1.month==event.dayStart.month){
            ret=true
        }
    }
    return ret
}
//=====================================================================================

//=====================================================================================
//=====================================================================================
//Функция проверки времени события
//Входные данные:
//                      hour:Int    - текущий час
//                      event:Event - событие
//Возращаемое значение:
//                      ret:Boolean - да/нет
//=====================================================================================
fun detectTime(hour: Int, event: Event):Boolean {
    var ret = false
    if(dateCompare(event.dayStart, event.dayFinish)==false){
        for (x in event.timeStart.hour until 24){
            if(x==hour){
                ret=true
            }
        }
    }
    else{
        for(x in event.timeStart.hour until event.timeFinish.hour){
            if(x==hour){
                ret=true
            }
        }
        if(event.timeFinish.minute>0){
            if(hour==event.timeFinish.hour){
                ret=true
            }
        }
    }
    return ret
}
//=====================================================================================

//=====================================================================================
//Функция проверки Соответствия категории и события
//Входные данные:
//                      categoryEvent: CategoryEvent    - Категория
//                      event:Event                     - событие
//Возращаемое значение:
//                      ret:Boolean - да/нет
//=====================================================================================
fun categoryDetect(categoryEvent: CategoryEvent, event: Event): Boolean{
    var ret=false
    for(x in event.type){
        if (x==categoryEvent.id){
            ret=true
        }
    }
    return ret
}


//#####################################################################################################################
//#####################################################################################################################
//###############################    Блок функций для окон создания и редактирования    ###############################
//#####################################################################################################################
//#####################################################################################################################


//=====================================================================================
//Функция проверки числа в формате строки
//Входные данные:
//                      number: String    - Число
//Возращаемое значение:
//                      ret:Boolean - да/нет
//=====================================================================================
fun numberStringDetect(number: String): Boolean{
    var ret = true
    for(i in number){
        var flag = false
        val ascii = listOf<Char>('0','1','2','3','4','5','6','7','8','9')
        for (x in ascii){
            if(i==x){
                flag=true
            }
        }
        if(flag==false){
            ret = false
        }
    }
    return ret
}
//=====================================================================================


//=====================================================================================
//Функция перевода строки в число
//Входные данные:
//                      number:String - строка
//Возращаемое значение:
//                      numberInt:Int - число
//=====================================================================================
fun asciiTranslate(number: String): Int{
    var numberInt = 0
    if(numberStringDetect(number)){
        for(x in number){
            numberInt*10
            numberInt+(x-48).toInt()
        }
    }
    return numberInt
}
//=====================================================================================


//=====================================================================================
//Функция проверки даты на корректность
//Входные данные:
//                      date:DateM  - дата
//Возращаемое значение:
//                      ret:Boolean - да/нет
//=====================================================================================
fun dateDetect(date: DateM):Boolean{
    var ret = true
    if(date.year<1500||date.year>3000){
        ret=false
    }
    else{
        val mmonth = IntArray(13)
        mmonth[1] = 31
        mmonth[2] = 28
        mmonth[3] = 31
        mmonth[4] = 30
        mmonth[5] = 31
        mmonth[6] = 30
        mmonth[7] = 31
        mmonth[8] = 31
        mmonth[9] = 30
        mmonth[10] = 31
        mmonth[11] = 30
        mmonth[12] = 31
        if(date.year%4==0){
            mmonth[2]=29
        }
        if(date.month<1||date.month>12){
            ret = false
        }
        else{
            if (date.day<1||date.day>mmonth[date.month]){
                ret =false
            }
        }
    }
    return ret
}
//=====================================================================================

//=====================================================================================
//Функция проверки времени на корректность
//Входные данные:
//                      time:TimeS  - время
//Возращаемое значение:
//                      ret:Boolean - да/нет
//=====================================================================================
fun timeDetect(time: TimeS):Boolean{
    var ret = true
    if(time.hour<0||time.hour>23){
        ret = false
    }
    else{
        if(time.minute<0||time.minute>59){
            ret=false
        }
        else{
            if(time.second<0||time.second>59){
                ret=false
            }
        }
    }
    return ret
}
//=====================================================================================
