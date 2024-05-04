package ma.shuler.solvetheexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.graphics.Color
import android.widget.Button
import android.widget.TextView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    lateinit var summary : TextView

    lateinit var correct : TextView
    lateinit var incorrect : TextView

    lateinit var percent : TextView

    lateinit var first : TextView
    lateinit var second : TextView
    lateinit var operation : TextView

    lateinit var answer : TextView

    lateinit var check : Button
    lateinit var start : Button

    var correctAns = 0
    var incorrectAns = 0

    val operations = listOf("+", "-", "*", "/")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        summary = findViewById(R.id.textView1)

        correct = findViewById(R.id.textView4)
        incorrect = findViewById(R.id.textView5)

        percent = findViewById(R.id.textView6)

        first = findViewById(R.id.textView7)
        second = findViewById(R.id.textView9)
        operation = findViewById(R.id.textView8)

        answer = findViewById(R.id.editTextText)


        check = findViewById<Button>(R.id.button)
        check.setOnClickListener {
            check()
        }

        start = findViewById<Button>(R.id.button1)
        start.setOnClickListener {
            start()
        }

        answer.isEnabled = false
        check.isEnabled = false
    }

    fun check() {
        check.isEnabled = false
        start.isEnabled = true
        answer.isEnabled = false

        val ans = getAns().toInt().toString()

        if (answer.text.toString() == ans) {
            correctAns++
            answer.setBackgroundColor(Color.GREEN)
        }
        else {
            incorrectAns++
            answer.setBackgroundColor(Color.RED)
        }
        correct.text = correctAns.toString()
        incorrect.text = incorrectAns.toString()
        summary.text = (correctAns + incorrectAns).toString()
        percent.text = String.format("%.2f", (correctAns.toDouble() / (correctAns + incorrectAns) * 100)) + "%"
    }

    fun start() {
        start.isEnabled = false
        check.isEnabled = true
        answer.isEnabled = true
        answer.setBackgroundColor(Color.TRANSPARENT)

        makeNewExpr()
    }

    fun makeNewExpr(){
        first.text = Random.nextInt(10, 100).toString()
        second.text = Random.nextInt(10, 100).toString()
        operation.text = operations[Random.nextInt(0, 4)]

        while (operation.text == "/" && first.text.toString().toDouble() % second.text.toString().toDouble() != 0.0)
        {
            first.text = Random.nextInt(10, 100).toString()
            second.text = Random.nextInt(10, 100).toString()
        }
    }

    fun getAns() : Double {
        when (operation.text){
            "+" -> {
                return first.text.toString().toDouble() + second.text.toString().toDouble()
            }
            "-" -> {
                return first.text.toString().toDouble() - second.text.toString().toDouble()
            }
            "*" -> {
                return first.text.toString().toDouble() * second.text.toString().toDouble()
            }
            "/" -> {
                return first.text.toString().toDouble() / second.text.toString().toDouble()
            }
        }
        return 0.0
    }
}