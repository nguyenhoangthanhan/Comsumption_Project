package gam.trainingcourse.annht2_advanceandroid_contentprovider_app1.model.normal

class InfoItemTimeTable{
    var idTimeTable:String = ""
    var nameTimeTable:String = ""
    var dayInWeekAndSubjects: DayInWeekAndSubjects = DayInWeekAndSubjects()

    constructor()

    constructor( idTimeTable:String,  nameTimeTable:String
                 ,  dayInWeekAndSubjects: DayInWeekAndSubjects){
        this.idTimeTable = idTimeTable
        this.nameTimeTable = nameTimeTable
        this.dayInWeekAndSubjects = dayInWeekAndSubjects
    }
}