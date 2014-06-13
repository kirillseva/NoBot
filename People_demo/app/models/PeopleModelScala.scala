package models
import anorm.SQL
import anorm.SqlQuery
import play.api.Play.current
import play.api.db.DB


case class People(
		id: Int,
		first_name: Option[String],
		last_name: Option[String],
		status: Option[String],
		office: Option[Int],
		phone: Option[String],
		email: Option[String],
		additional_info: Option[String]
)

object People {
val sql: SqlQuery = SQL("SELECT * FROM people")

def getAll = DB.withConnection { 
	implicit connection =>
	sql().map (row =>
		People(row[Int]("id"), row[Option[String]]("first_name"), row[Option[String]]("last_name"), row[Option[String]]("status"),
			row[Option[Int]]("office"), row[Option[String]]("phone"), row[Option[String]]("email"), row[Option[String]]("additional_info"))
			).toList
}
}