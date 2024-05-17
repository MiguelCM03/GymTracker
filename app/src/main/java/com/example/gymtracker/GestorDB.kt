import android.content.Context
import android.database.Cursor
import com.example.gymtracker.DatabaseHelper

class GestorDB(context: Context) {

    private val dbHelper = DatabaseHelper(context, "USUARIOS", null, 1)

    fun getData(nombreTabla: String, campos: List<String>): List<String> {
        val db = dbHelper.readableDatabase
        val cursor: Cursor = db.query(
            nombreTabla,
            arrayOf("id", "name"),
            null,
            null,
            null,
            null,
            null
        )

        val items = mutableListOf<String>()
        with(cursor) {
            while (moveToNext()) {
                val name = getString(getColumnIndexOrThrow("name"))
                items.add(name)
            }
        }
        cursor.close()
        return items
    }
}
