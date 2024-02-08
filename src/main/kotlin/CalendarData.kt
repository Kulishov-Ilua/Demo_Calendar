import java.awt.Color

//#####################################################################################################################
//#####################################################################################################################
//###############################               Календарь - база данных                 ###############################
//#####################################################################################################################
//####    Автор: Кулишов Илья Вячеславович    #########################################################################
//####    Версия: v.0.0.0.6                   #########################################################################
//####    Дата: 04.02.2024                    #########################################################################
//#####################################################################################################################
//#####################################################################################################################


//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//Класс для хранения информации о месяцах в календаре                      ~~~~~~~~~~~~
//Поля:                                                                    ~~~~~~~~~~~~
//      name:String - Название                                             ~~~~~~~~~~~~
//      kolday:Int  - Количество дней в месяце                             ~~~~~~~~~~~~
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
class month(name:String, kolday:Int){
    var name:String=name
    var kolday:Int=kolday
}
//#####################################################################################
//Массив для хранения месяцев                                              ############
//#####################################################################################
var calendar = listOf(
    month("January", 31),
    month("February", 28),
    month("Marth", 31),
    month("April", 30),
    month("May", 31),
    month("June", 30),
    month("July", 31),
    month("August", 31),
    month("September", 30),
    month("October", 31),
    month("November", 30),
    month("December", 31),
)
//#####################################################################################

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//класс для хранения даты                                                  ~~~~~~~~~~~~
//Поля:                                                                    ~~~~~~~~~~~~
//      day:Int     - День                                                 ~~~~~~~~~~~~
//      month:Int   - Месяц                                                ~~~~~~~~~~~~
//      year:Int    - Год                                                  ~~~~~~~~~~~~
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
class DateM(day:Int,month:Int, year:Int){
    var day: Int = day
    var month: Int = month
    var year: Int = year
}
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//класс для хранения времени                                               ~~~~~~~~~~~~
//Поля:                                                                    ~~~~~~~~~~~~
//      hour:Int     - Час                                                 ~~~~~~~~~~~~
//      minute:Int   - Минута                                              ~~~~~~~~~~~~
//      second:Int   - Секунда                                             ~~~~~~~~~~~~
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
class TimeS(hour:Int, minute:Int, second:Int){
    var hour:Int=hour
    var minute:Int=minute
    var second:Int = second
}
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//класс для хранения событий                                               ~~~~~~~~~~~~
//Поля:                                                                    ~~~~~~~~~~~~
//      name:String         - Имя мероприятия                              ~~~~~~~~~~~~
//      dayStart:DateM      - День начала                                  ~~~~~~~~~~~~
//      dayFinish:DateM     - День конца                                   ~~~~~~~~~~~~
//      timeStart:TimeS     - Время начала                                 ~~~~~~~~~~~~
//      timeFinish:TimeS    - Время окончания                              ~~~~~~~~~~~~
//      povt:Int            - Повторение(0-нет|1-неделя|2-месяц|3-год)     ~~~~~~~~~~~~
//      remind:Int          - Напоминание                                  ~~~~~~~~~~~~
//      type:String         - Тип                                          ~~~~~~~~~~~~
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
class Event(name:String,dayStart:DateM,dayFinish:DateM, timeStart:TimeS, timeFinish:TimeS, povt:Int, remind:Int, type: String){
    var name:String = name
    var dayStart:DateM = dayStart
    var dayFinish:DateM = dayFinish
    var timeStart:TimeS=timeStart
    var timeFinish:TimeS=timeFinish
    var povt:Int=povt
    var remind:Int = remind
    var type:String = type
}
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

//#####################################################################################
//Массив для хранения событий                                              ############
//#####################################################################################
var events = listOf<Event>(
    Event("hello1_false_",DateM(1,2,2024),DateM(1,2,2024),
        TimeS(0,0,0), TimeS(23,23,23),1,0,"1"
        ),
    Event("hello2_false_",DateM(2,2,2024),DateM(2,2,2024),
        TimeS(0,0,0), TimeS(5,0,0),0,0,"2"
        ),
    Event("hello3_True_",DateM(2,2,2024),DateM(2,2,2024),
        TimeS(0,0,0), TimeS(23,0,0),0,0,"1"
        ),
    Event("hello5_True_",DateM(2,2,2024),DateM(2,2,2024),
        TimeS(0,0,0), TimeS(23,0,0),0,0,"12"
    ),
    Event("hello4_true_",DateM(2,2,2001),DateM(2,2,2026),
        TimeS(0,0,0), TimeS(5,0,0),2,0,"0"
        ),
)
//#####################################################################################


//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//класс для хранения категорий событий                                     ~~~~~~~~~~~~
//Поля:                                                                    ~~~~~~~~~~~~
//      name:String         - Имя категории                                ~~~~~~~~~~~~
//      status:Int          - Статус                                       ~~~~~~~~~~~~
//      color:Color         - Цвет                                         ~~~~~~~~~~~~
//      team:String         - У кого доступ                                ~~~~~~~~~~~~
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
class CategoryEvent(id:Char, name: String,status: Int,color: Color,team:String){
    var id:Char = id
    var name:String=name
    var status:Int=status
    var color:Color=color
    var team:String=team
}
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//#####################################################################################
//Массив для хранения категорий событий                                    ############
//#####################################################################################
var categoryEvents = mutableListOf<CategoryEvent>(
    CategoryEvent('1',"Встречи", 1, Color.BLUE, "1"),
    CategoryEvent('2',"Кофебрейки", 1, Color.PINK, "1"),
)
//#####################################################################################


//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//класс для хранения минизадач                                             ~~~~~~~~~~~~
//Поля:                                                                    ~~~~~~~~~~~~
//      name:String         - Имя мероприятия                              ~~~~~~~~~~~~
//      status:Int          - Статус                                       ~~~~~~~~~~~~
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
class MiniTask(name:String, status:Int){
    var name:String = name
    var status:Int = status
}
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//класс для хранения задач                                                 ~~~~~~~~~~~~
//Поля:                                                                    ~~~~~~~~~~~~
//      name:String             - Имя задачи                               ~~~~~~~~~~~~
//      dayStart:DateM          - День начала                              ~~~~~~~~~~~~
//      dayFinish:DateM         - День конца                               ~~~~~~~~~~~~
//      timeStart:TimeS         - Время начала                             ~~~~~~~~~~~~
//      timeFinish:TimeS        - Время окончания                          ~~~~~~~~~~~~
//      povt:Int                - Повторение                               ~~~~~~~~~~~~
//      remind:Int              - Напоминание                              ~~~~~~~~~~~~
//      type:String             - Тип                                      ~~~~~~~~~~~~
//      description:String      - Напоминание                              ~~~~~~~~~~~~
//      status:Int              - Статус                                   ~~~~~~~~~~~~
//      tasklist:List<MiniTask> - Список подзадач                          ~~~~~~~~~~~~
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
class Task (name:String,dayStart:DateM,dayFinish:DateM, timeStart:TimeS, timeFinish:TimeS,
            povt:Int,remind:Int, type: String, description:String, status: Int,
            tasklist:List<MiniTask>){
    var name:String = name
    var dayStart:DateM = dayStart
    var dayFinish:DateM = dayFinish
    var timeStart:TimeS=timeStart
    var timeFinish:TimeS=timeFinish
    var povt:Int=povt
    var remind:Int = remind
    var type:String = type
    var description:String =description
    var status:Int = status
    var tasklist:List<MiniTask> = tasklist
}
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

//#####################################################################################
//Массив для хранения задач                                                ############
//#####################################################################################
var tasks = listOf<Task>()
//#####################################################################################


