import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import java.awt.SystemColor
//import java.awt.Color
import java.time.LocalDate
import java.time.YearMonth
import java.awt.SystemColor.text




//#####################################################################################################################
//#####################################################################################################################
//###############################                      Календарь                        ###############################
//#####################################################################################################################
//####    Автор: Кулишов Илья Вячеславович    #########################################################################
//####    Версия: v.0.0.0.7                   #########################################################################
//####    Дата: 04.02.2024                    #########################################################################
//#####################################################################################################################
//#####################################################################################################################

//#####################################################################################################################
//#####################################################################################################################
//###############################                   Composable block                    ###############################
//#####################################################################################################################
//#####################################################################################################################
var screenCalendar = mutableStateOf(0)
var dayCalendarOneDay = mutableStateOf(DateM(0,0,0))
var categoryEventL= derivedStateOf { (categoryEvents) }

//#####################################################################################################################
//###############################                    Основные экраны                    ###############################
//#####################################################################################################################

//=====================================================================================
//Экран календаря
//=====================================================================================
@Composable
fun CalendarScreen(){
    //---------------------------------------------------------------------
    //работа с текущей датой
    //---------------------------------------------------------------------

    //var now= remember { mutableStateOf( YearMonth.now()) }
    var nowYear = remember { mutableStateOf( YearMonth.now().year) }
    var nowMonth = remember { mutableStateOf( YearMonth.now().monthValue) }
    var nowDay = remember { mutableStateOf( LocalDate.now().dayOfMonth) }

    Column {
        //---------------------------------------------------------------------
        //Кнопки переключения месяца(работают если экран общего календаря
        //---------------------------------------------------------------------
        if(screenCalendar.value==0) {
            Box(
                modifier = Modifier.fillMaxWidth().height(50.dp),
                contentAlignment = Alignment.Center
            ) {
                Row {
                    Box(
                        Modifier.padding(15.dp).background(androidx.compose.ui.graphics.Color.White)
                            .clickable {
                                if (screenCalendar.value == 0) {
                                    if (nowMonth.value == 1) {
                                        nowMonth.value = 12
                                        nowYear.value = nowYear.value - 1
                                    } else {
                                        nowMonth.value = nowMonth.value - 1
                                    }
                                }
                            }) {
                        Text(
                            "Назад",
                            style = TextStyle(
                                color = androidx.compose.ui.graphics.Color.Black
                            )
                        )

                    }
                    Text(
                        text = calendar[nowMonth.value - 1].name, style = TextStyle(
                            color = androidx.compose.ui.graphics.Color.White, fontSize = 20.sp
                        )
                    )
                    Box(
                        Modifier.padding(15.dp).background(androidx.compose.ui.graphics.Color.White)
                            .clickable {
                                if (screenCalendar.value == 0) {
                                    if (nowMonth.value == 12) {
                                        nowMonth.value = 1
                                        nowYear.value = nowYear.value + 1
                                    } else {
                                        nowMonth.value = nowMonth.value + 1
                                    }
                                }
                            }) {
                        Text(
                            "Вперед",
                            style = TextStyle(color = androidx.compose.ui.graphics.Color.Black)
                        )
                    }
                }
            }
        }
        Row {
            var transfer = remember { derivedStateOf { (DateM(nowDay.value, nowMonth.value, nowYear.value)) } }
            CalendarBox1(transfer.value)
        }
    }

}
//=====================================================================================

//=====================================================================================
//Календарь месяца
//Входные данные:
//                  date:DateM  - дата текущего месяца
//=====================================================================================
@Composable
fun CalendarBox1(date:DateM){

    //---------------------------------------------------------------------------------------------
    //календарь инициализация
    //---------------------------------------------------------------------------------------------
    var arrayCalendar = remember {
        mutableStateOf(Array(5){IntArray(7)})
    }
    var first=showWeekDay(date.year,date.month,1)
    var dayNum=1;
    for (i in 0 until 7){
        if(first==0){
            arrayCalendar.value[0][i]=dayNum
            dayNum++
        }
        else{
            arrayCalendar.value[0][i]=0
            first--
        }
    }
    if (date.year%4==0){
        calendar[1].kolday=29
    }
    else{
        calendar[1].kolday=28
    }
    for (i in 1 until 5){
        for (x in 0 until 7){
            if (dayNum <= calendar[date.month-1].kolday) {
                arrayCalendar.value[i][x] = dayNum
                dayNum++
            } else {
                arrayCalendar.value[i][x] =0
            }
        }
    }
    //---------------------------------------------------------------------------------------------
    Box(modifier = Modifier.padding(50.dp).fillMaxSize()){
        Column(){
            Row() {
                Box(
                    modifier = Modifier.fillMaxWidth(0.14f).fillMaxHeight(0.2f)/*.fillMaxWidth()*/
                        .border(width = 3.dp, color = androidx.compose.ui.graphics.Color.White)
                ) {
                    val dayTrans=DateM(arrayCalendar.value[0][0], date.month,date.year)
                    calendarDay(dayTrans,categoryEventL.value)
                }
                Box(
                    modifier = Modifier.fillMaxWidth(1.0f /0.86f * 0.14f).fillMaxHeight(0.2f)/*.fillMaxWidth()*/
                        .border(width = 3.dp, color = androidx.compose.ui.graphics.Color.White)
                ) {
                    val dayTrans=DateM(arrayCalendar.value[0][1], date.month,date.year)
                    calendarDay(dayTrans,categoryEventL.value)
                    //Text("hello")
                }
                Box(
                    modifier = Modifier.fillMaxWidth(1.0f /0.72f * 0.14f).fillMaxHeight(0.2f)/*.fillMaxWidth()*/
                        .border(width = 3.dp, color = androidx.compose.ui.graphics.Color.White)
                ) {
                    val dayTrans=DateM(arrayCalendar.value[0][2], date.month,date.year)
                    calendarDay(dayTrans,categoryEventL.value)
                    //Text("hello")
                }
                Box(
                    modifier = Modifier.fillMaxWidth(1.0f /0.58f * 0.14f).fillMaxHeight(0.2f)/*.fillMaxWidth()*/
                        .border(width = 3.dp, color = androidx.compose.ui.graphics.Color.White)
                ) {
                    val dayTrans=DateM(arrayCalendar.value[0][3], date.month,date.year)
                    calendarDay(dayTrans,categoryEventL.value)
                    //Text("hello")
                }
                Box(
                    modifier = Modifier.fillMaxWidth(1.0f /0.44f * 0.14f).fillMaxHeight(0.2f)/*.fillMaxWidth()*/
                        .border(width = 3.dp, color = androidx.compose.ui.graphics.Color.White)
                ) {
                    val dayTrans=DateM(arrayCalendar.value[0][4], date.month,date.year)
                    calendarDay(dayTrans,categoryEventL.value)
                    //Text("hello")
                }
                Box(
                    modifier = Modifier.fillMaxWidth(1.0f /0.30f * 0.14f).fillMaxHeight(0.2f)/*.fillMaxWidth()*/
                        .border(width = 3.dp, color = androidx.compose.ui.graphics.Color.White)
                ) {
                    val dayTrans=DateM(arrayCalendar.value[0][5], date.month,date.year)
                    calendarDay(dayTrans,categoryEventL.value)
                    //Text("hello")
                }
                Box(
                    modifier = Modifier.fillMaxWidth().fillMaxHeight(0.2f)/*.fillMaxWidth()*/
                        .border(width = 3.dp, color = androidx.compose.ui.graphics.Color.White)
                ) {
                    val dayTrans=DateM(arrayCalendar.value[0][6], date.month,date.year)
                    calendarDay(dayTrans,categoryEventL.value)
                    //Text("hello")
                }
            }

            Row() {
                Box(
                    modifier = Modifier.fillMaxWidth(0.14f).fillMaxHeight(1.0f /0.8f * 0.2f)/*.fillMaxWidth()*/
                        .border(width = 3.dp, color = androidx.compose.ui.graphics.Color.White)
                ) {
                    val dayTrans=DateM(arrayCalendar.value[1][0], date.month,date.year)
                    calendarDay(dayTrans,categoryEvents)
                    //Text("hello")
                }
                Box(
                    modifier = Modifier.fillMaxWidth(1.0f /0.86f * 0.14f).fillMaxHeight(1.0f /0.8f * 0.2f)/*.fillMaxWidth()*/
                        .border(width = 3.dp, color = androidx.compose.ui.graphics.Color.White)
                ) {
                    val dayTrans=DateM(arrayCalendar.value[1][1], date.month,date.year)
                    calendarDay(dayTrans,categoryEvents)
                    //Text("hello")
                }
                Box(
                    modifier = Modifier.fillMaxWidth(1.0f /0.72f * 0.14f).fillMaxHeight(1.0f /0.8f * 0.2f)/*.fillMaxWidth()*/
                        .border(width = 3.dp, color = androidx.compose.ui.graphics.Color.White)
                ) {
                    val dayTrans=DateM(arrayCalendar.value[1][2], date.month,date.year)
                    calendarDay(dayTrans,categoryEvents)
                    //Text("hello")
                }
                Box(
                    modifier = Modifier.fillMaxWidth(1.0f /0.58f * 0.14f).fillMaxHeight(1.0f /0.8f * 0.2f)/*.fillMaxWidth()*/
                        .border(width = 3.dp, color = androidx.compose.ui.graphics.Color.White)
                ) {
                    val dayTrans=DateM(arrayCalendar.value[1][3], date.month,date.year)
                    calendarDay(dayTrans,categoryEvents)
                    //Text("hello")
                }
                Box(
                    modifier = Modifier.fillMaxWidth(1.0f /0.44f * 0.14f).fillMaxHeight(1.0f /0.8f * 0.2f)/*.fillMaxWidth()*/
                        .border(width = 3.dp, color = androidx.compose.ui.graphics.Color.White)
                ) {
                    val dayTrans=DateM(arrayCalendar.value[1][4], date.month,date.year)
                    calendarDay(dayTrans,categoryEvents)
                    //Text("hello")
                }
                Box(
                    modifier = Modifier.fillMaxWidth(1.0f /0.30f * 0.14f).fillMaxHeight(1.0f /0.8f * 0.2f)/*.fillMaxWidth()*/
                        .border(width = 3.dp, color = androidx.compose.ui.graphics.Color.White)
                ) {
                    val dayTrans=DateM(arrayCalendar.value[1][5], date.month,date.year)
                    calendarDay(dayTrans,categoryEvents)
                    //Text("hello")
                }
                Box(
                    modifier = Modifier.fillMaxWidth().fillMaxHeight(1.0f /0.8f * 0.2f)/*.fillMaxWidth()*/
                        .border(width = 3.dp, color = androidx.compose.ui.graphics.Color.White)
                ) {
                    val dayTrans=DateM(arrayCalendar.value[1][6], date.month,date.year)
                    calendarDay(dayTrans,categoryEvents)
                    //Text("hello")
                }
            }
            Row() {
                Box(
                    modifier = Modifier.fillMaxWidth(0.14f).fillMaxHeight(1.0f /0.6f * 0.2f)/*.fillMaxWidth()*/
                        .border(width = 3.dp, color = androidx.compose.ui.graphics.Color.White)
                ) {
                    val dayTrans=DateM(arrayCalendar.value[2][0], date.month,date.year)
                    calendarDay(dayTrans,categoryEvents)
                    //Text("hello")
                }
                Box(
                    modifier = Modifier.fillMaxWidth(1.0f /0.86f * 0.14f).fillMaxHeight(1.0f /0.6f * 0.2f)/*.fillMaxWidth()*/
                        .border(width = 3.dp, color = androidx.compose.ui.graphics.Color.White)
                ) {
                    val dayTrans=DateM(arrayCalendar.value[2][1], date.month,date.year)
                    calendarDay(dayTrans,categoryEvents)
                    //Text("hello")
                }
                Box(
                    modifier = Modifier.fillMaxWidth(1.0f /0.72f * 0.14f).fillMaxHeight(1.0f /0.6f * 0.2f)/*.fillMaxWidth()*/
                        .border(width = 3.dp, color = androidx.compose.ui.graphics.Color.White)
                ) {
                    val dayTrans=DateM(arrayCalendar.value[2][2], date.month,date.year)
                    calendarDay(dayTrans,categoryEvents)
                    //Text("hello")
                }
                Box(
                    modifier = Modifier.fillMaxWidth(1.0f /0.58f * 0.14f).fillMaxHeight(1.0f /0.6f * 0.2f)/*.fillMaxWidth()*/
                        .border(width = 3.dp, color = androidx.compose.ui.graphics.Color.White)
                ) {
                    val dayTrans=DateM(arrayCalendar.value[2][3], date.month,date.year)
                    calendarDay(dayTrans,categoryEvents)
                    //Text("hello")
                }
                Box(
                    modifier = Modifier.fillMaxWidth(1.0f /0.44f * 0.14f).fillMaxHeight(1.0f /0.6f * 0.2f)/*.fillMaxWidth()*/
                        .border(width = 3.dp, color = androidx.compose.ui.graphics.Color.White)
                ) {
                    val dayTrans=DateM(arrayCalendar.value[2][4], date.month,date.year)
                    calendarDay(dayTrans,categoryEvents)
                    //Text("hello")
                }
                Box(
                    modifier = Modifier.fillMaxWidth(1.0f /0.30f * 0.14f).fillMaxHeight(1.0f /0.6f * 0.2f)/*.fillMaxWidth()*/
                        .border(width = 3.dp, color = androidx.compose.ui.graphics.Color.White)
                ) {
                    val dayTrans=DateM(arrayCalendar.value[2][5], date.month,date.year)
                    calendarDay(dayTrans,categoryEvents)
                    //Text("hello")
                }
                Box(
                    modifier = Modifier.fillMaxWidth().fillMaxHeight(1.0f /0.6f * 0.2f)/*.fillMaxWidth()*/
                        .border(width = 3.dp, color = androidx.compose.ui.graphics.Color.White)
                ) {
                    val dayTrans=DateM(arrayCalendar.value[2][6], date.month,date.year)
                    calendarDay(dayTrans,categoryEvents)
                    //Text("hello")
                }
            }
            Row() {
                Box(
                    modifier = Modifier.fillMaxWidth(0.14f).fillMaxHeight(1.0f /0.4f * 0.2f)/*.fillMaxWidth()*/
                        .border(width = 3.dp, color = androidx.compose.ui.graphics.Color.White)
                ) {
                    val dayTrans=DateM(arrayCalendar.value[3][0], date.month,date.year)
                    calendarDay(dayTrans,categoryEvents)
                    //Text("hello")
                }
                Box(
                    modifier = Modifier.fillMaxWidth(1.0f /0.86f * 0.14f).fillMaxHeight(1.0f /0.4f * 0.2f)/*.fillMaxWidth()*/
                        .border(width = 3.dp, color = androidx.compose.ui.graphics.Color.White)
                ) {
                    val dayTrans=DateM(arrayCalendar.value[3][1], date.month,date.year)
                    calendarDay(dayTrans,categoryEvents)
                    //Text("hello")
                }
                Box(
                    modifier = Modifier.fillMaxWidth(1.0f /0.72f * 0.14f).fillMaxHeight(1.0f /0.4f * 0.2f)/*.fillMaxWidth()*/
                        .border(width = 3.dp, color = androidx.compose.ui.graphics.Color.White)
                ) {
                    val dayTrans=DateM(arrayCalendar.value[3][2], date.month,date.year)
                    calendarDay(dayTrans,categoryEvents)
                    //Text("hello")
                }
                Box(
                    modifier = Modifier.fillMaxWidth(1.0f /0.58f * 0.14f).fillMaxHeight(1.0f /0.4f * 0.2f)/*.fillMaxWidth()*/
                        .border(width = 3.dp, color = androidx.compose.ui.graphics.Color.White)
                ) {
                    val dayTrans=DateM(arrayCalendar.value[3][3], date.month,date.year)
                    calendarDay(dayTrans,categoryEvents)
                    //Text("hello")
                }
                Box(
                    modifier = Modifier.fillMaxWidth(1.0f /0.44f * 0.14f).fillMaxHeight(1.0f /0.4f * 0.2f)/*.fillMaxWidth()*/
                        .border(width = 3.dp, color = androidx.compose.ui.graphics.Color.White)
                ) {
                    val dayTrans=DateM(arrayCalendar.value[3][4], date.month,date.year)
                    calendarDay(dayTrans,categoryEvents)
                    //Text("hello")
                }
                Box(
                    modifier = Modifier.fillMaxWidth(1.0f /0.30f * 0.14f).fillMaxHeight(1.0f /0.4f * 0.2f)/*.fillMaxWidth()*/
                        .border(width = 3.dp, color = androidx.compose.ui.graphics.Color.White)
                ) {
                    val dayTrans=DateM(arrayCalendar.value[3][5], date.month,date.year)
                    calendarDay(dayTrans,categoryEvents)
                    //Text("hello")
                }
                Box(
                    modifier = Modifier.fillMaxWidth().fillMaxHeight(1.0f /0.4f * 0.2f)/*.fillMaxWidth()*/
                        .border(width = 3.dp, color = androidx.compose.ui.graphics.Color.White)
                ) {
                    val dayTrans=DateM(arrayCalendar.value[3][6], date.month,date.year)
                    calendarDay(dayTrans,categoryEvents)
                    //Text("hello")
                }
            }
            Row() {
                Box(
                    modifier = Modifier.fillMaxWidth(0.14f).fillMaxHeight()/*.fillMaxWidth()*/
                        .border(width = 3.dp, color = androidx.compose.ui.graphics.Color.White)
                ) {
                    val dayTrans=DateM(arrayCalendar.value[4][0], date.month,date.year)
                    calendarDay(dayTrans,categoryEvents)
                    //Text("hello")
                }
                Box(
                    modifier = Modifier.fillMaxWidth(1.0f /0.86f * 0.14f).fillMaxHeight()/*.fillMaxWidth()*/
                        .border(width = 3.dp, color = androidx.compose.ui.graphics.Color.White)
                ) {
                    val dayTrans=DateM(arrayCalendar.value[4][1], date.month,date.year)
                    calendarDay(dayTrans,categoryEvents)
                    //Text("hello")
                }
                Box(
                    modifier = Modifier.fillMaxWidth(1.0f /0.72f * 0.14f).fillMaxHeight()/*.fillMaxWidth()*/
                        .border(width = 3.dp, color = androidx.compose.ui.graphics.Color.White)
                ) {
                    val dayTrans=DateM(arrayCalendar.value[4][2], date.month,date.year)
                    calendarDay(dayTrans,categoryEvents)
                    //Text("hello")
                }
                Box(
                    modifier = Modifier.fillMaxWidth(1.0f /0.58f * 0.14f).fillMaxHeight()/*.fillMaxWidth()*/
                        .border(width = 3.dp, color = androidx.compose.ui.graphics.Color.White)
                ) {
                    val dayTrans=DateM(arrayCalendar.value[4][3], date.month,date.year)
                    calendarDay(dayTrans,categoryEvents)
                    //Text("hello")
                }
                Box(
                    modifier = Modifier.fillMaxWidth(1.0f /0.44f * 0.14f).fillMaxHeight()/*.fillMaxWidth()*/
                        .border(width = 3.dp, color = androidx.compose.ui.graphics.Color.White)
                ) {
                    val dayTrans=DateM(arrayCalendar.value[4][4], date.month,date.year)
                    calendarDay(dayTrans,categoryEvents)
                    //Text("hello")
                }
                Box(
                    modifier = Modifier.fillMaxWidth(1.0f /0.30f * 0.14f).fillMaxHeight()/*.fillMaxWidth()*/
                        .border(width = 3.dp, color = androidx.compose.ui.graphics.Color.White)
                ) {
                    val dayTrans=DateM(arrayCalendar.value[4][5], date.month,date.year)
                    calendarDay(dayTrans,categoryEvents)
                    //Text("hello")
                }
                Box(
                    modifier = Modifier.fillMaxWidth().fillMaxHeight()/*.fillMaxWidth()*/
                        .border(width = 3.dp, color = androidx.compose.ui.graphics.Color.White)
                ) {
                    val dayTrans=DateM(arrayCalendar.value[4][6], date.month,date.year)
                    calendarDay(dayTrans,categoryEvents)
                    //Text("hello")
                }
            }
        }
    }
}
//=====================================================================================




//#####################################################################################################################
//###############################               Вспомогательные элементы                ###############################
//#####################################################################################################################
//###############################                   Календарь месяца                    ###############################
//#####################################################################################################################

//=====================================================================================
//День месяца в календаре месяца
//Входные данные:
//                  date:DateM  - дата дня месяца
//=====================================================================================
@Composable
fun calendarDay(date:DateM, eventscat2:List<CategoryEvent>){
    val now= YearMonth.now()
    val nowYear =  now.year
    val nowMonth =now.month.value
    val nowDay = LocalDate.now().dayOfMonth
    var bord= remember { mutableStateOf(0) }
    if(date.year==nowYear&&date.month==nowMonth&&date.day==nowDay){
        bord.value=1
    }
    else{
        bord.value=0
    }
    Box(modifier = Modifier.fillMaxSize().clickable {
        dayCalendarOneDay.value=date
        screenCalendar.value=1
    }){
        Column {
            Box(modifier = Modifier.padding(5.dp).fillMaxWidth()){
                Text(text = if(date.day!=0) date.day.toString() else "",
                    style = TextStyle(
                        color = if(bord.value==0) androidx.compose.ui.graphics.Color.White
                        else androidx.compose.ui.graphics.Color.Red
                    )
                )
            }

        }
    }
}
//=====================================================================================

