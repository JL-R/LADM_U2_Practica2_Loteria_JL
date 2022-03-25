package mx.tecnm.tepic.ladm_u2_practica2_loteria

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import kotlinx.coroutines.*
import mx.tecnm.tepic.ladm_u2_practica2_loteria.databinding.ActivityMainBinding
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var etH: TextView
   /* val mensajes= arrayOf("El Gallo","El Diablito","La Dama","El catrín",
        "El paraguas","La sirena","La escalera","La botella","El barril",
        "El árbol","El melón","El valiente","El gorrito","La muerte","La pera",
        "La bandera","El bandolón","El violoncello","La garza","El pájaro",
        "La mano","La bota","La luna","El cotorro","El borracho","El negrito",
        "El corazón","La sandía","El tambor","El camarón","Las jaras",
        "El músico","La araña","El soldado","La estrella","El cazo",
        "El mundo","El apache","El nopal","El alacrán","La rosa",
        "La calavera","La campana","El cantarito","El venado","El sol",
        "La corona","La chalupa","El pino","El pescado","La palma","La maceta",
        "El arpa","La rana")*/

    var mazo: Array <Imagen> = arrayOf(
        Imagen("El Gallo", R.drawable.carta1),
        Imagen("El Diablito", R.drawable.carta2),
        Imagen("La Dama", R.drawable.carta3),
        Imagen("El catrín", R.drawable.carta4),
        Imagen("El Paraguas", R.drawable.carta5),
        Imagen("La sirena", R.drawable.carta6),
        Imagen("La escalera", R.drawable.carta7),
        Imagen("La botella", R.drawable.carta8),
        Imagen("El barril", R.drawable.carta9),
        Imagen("El arbol", R.drawable.carta10),
        Imagen("El melon", R.drawable.carta11),
        Imagen("El valiente", R.drawable.carta12),
        Imagen("El gorrito", R.drawable.carta13),
        Imagen("La muerte", R.drawable.carta14),
        Imagen("La pera", R.drawable.carta15),
        Imagen("La bandera", R.drawable.carta16),
        Imagen("El bandolón", R.drawable.carta17),
        Imagen("El violoncello", R.drawable.carta18),
        Imagen("La Garza", R.drawable.carta19),
        Imagen("El pajaro", R.drawable.carta20),
        Imagen("La mano", R.drawable.carta21),
        Imagen("La bota", R.drawable.carta22),
        Imagen("La luna", R.drawable.carta23),
        Imagen("El cotorro", R.drawable.carta24),
        Imagen("El borracho", R.drawable.carta25),
        Imagen("El negrito", R.drawable.carta26),
        Imagen("El corazon", R.drawable.carta27),
        Imagen("La sandía", R.drawable.carta28),
        Imagen("El tambór", R.drawable.carta29),
        Imagen("El camarón", R.drawable.carta30),
        Imagen("Las jaras", R.drawable.carta31),
        Imagen("El musico", R.drawable.carta32),
        Imagen("La araña", R.drawable.carta33),
        Imagen("El soldado", R.drawable.carta34),
        Imagen("La araña", R.drawable.carta35),
        Imagen("El cazo", R.drawable.carta36),
        Imagen("El mundo", R.drawable.carta37),
        Imagen("El apache", R.drawable.carta38),
        Imagen("El nopal", R.drawable.carta39),
        Imagen("El alacrán", R.drawable.carta40),
        Imagen("La rosa", R.drawable.carta41),
        Imagen("La calavera", R.drawable.carta42),
        Imagen("La campana", R.drawable.carta43),
        Imagen("El cantarito", R.drawable.carta44),
        Imagen("El venado", R.drawable.carta45),
        Imagen("El sol", R.drawable.carta46),
        Imagen("La corona", R.drawable.carta47),
        Imagen("La chalupa", R.drawable.carta48),
        Imagen("EL pino", R.drawable.carta49),
        Imagen("EL pescado", R.drawable.carta50),
        Imagen("La palma", R.drawable.carta51),
        Imagen("La maceta", R.drawable.carta52),
        Imagen("El arpa", R.drawable.carta53),
        Imagen("La rana", R.drawable.carta54),
    )
    var contador=0
    var proceso= true
    var pausar= false


    val scope= CoroutineScope(Job() + Dispatchers.Main)

    val objetoCoroutineControlada= scope.launch(EmptyCoroutineContext, CoroutineStart.LAZY){
        while (true){
            runOnUiThread {
                if(!pausar){
                    //binding.imagenLoteria.setImageResource(mazo[contador].imagen)
                    binding.imagenLoteria.setImageResource(mazo[contador].carta)
                    binding.etLoteria.text=mazo[contador++].name
                }
            }
            if(contador== mazo.size){
                pausar=true
                proceso=false
                setTitle("¿Nuevo Juego?")
                binding.btnIniciar.isEnabled=true
                binding.btnverificar.isEnabled=false
                binding.btnSuspender.isEnabled=false
            }
            delay(1000L)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        etH=findViewById(R.id.et_numero)
        //llenarMazo()

        binding.btnIniciar.setOnClickListener {
            if (proceso){

                aleatorio(mazo)
                objetoCoroutineControlada.start()
                binding.btnSuspender.isEnabled=true
                //Hilo(etH).start()
            }else{
                setTitle("se va y se corre con la vieja del pozole")
                if(contador== mazo.size){
                    contador=0
                    pausar=false
                    proceso=true
                    aleatorio(mazo)
                    //Hilo(etH).start()
                }
            }
            binding.btnSuspender.isEnabled=true
        }

        binding.btnSuspender.setOnClickListener {
            binding.btnIniciar.isEnabled=false
            binding.btnSuspender.isEnabled=false
            if(objetoCoroutineControlada.isActive){
                setTitle("LOTERIA!!")
                binding.btnverificar.isEnabled=true
                pausar=true
                return@setOnClickListener
            }

/*
            while (proceso==true){
                if(objetoCoroutineControlada.isActive){
                    objetoCoroutineControlada.cancel()
                    return@setOnClickListener

                }
            }

            if(objetoCoroutineControlada.isCancelled){
                //se ejecuta cuando se ejecutó un cancel
                setTitle("Loteria??")
                return@setOnClickListener
            }
            */

        }
        binding.btnverificar.setOnClickListener {
            setTitle("Cartas Faltantes")
            binding.btnIniciar.isEnabled=false
            binding.btnSuspender.isEnabled=false
            pausar=false
            return@setOnClickListener

        }
    }

    fun aleatorio(lista: Array<Imagen>){
        lista.shuffle()
    }
    /*private fun rand(hasta:Int): Int{
        return Random.nextInt(hasta)
    }*/

    /*
    fun llenarMazo(){
        mazo.add(Imagen(R.drawable.carta1))
        mazo.add(Imagen(R.drawable.carta2))
        mazo.add(Imagen(R.drawable.carta3))
        mazo.add(Imagen(R.drawable.carta4))
        mazo.add(Imagen(R.drawable.carta5))
        mazo.add(Imagen(R.drawable.carta6))
        mazo.add(Imagen(R.drawable.carta7))
        mazo.add(Imagen(R.drawable.carta8))
        mazo.add(Imagen(R.drawable.carta9))
        mazo.add(Imagen(R.drawable.carta10))
        mazo.add(Imagen(R.drawable.carta11))
        mazo.add(Imagen(R.drawable.carta12))
        mazo.add(Imagen(R.drawable.carta13))
        mazo.add(Imagen(R.drawable.carta14))
        mazo.add(Imagen(R.drawable.carta15))
        mazo.add(Imagen(R.drawable.carta16))
        mazo.add(Imagen(R.drawable.carta17))
        mazo.add(Imagen(R.drawable.carta18))
        mazo.add(Imagen(R.drawable.carta19))
        mazo.add(Imagen(R.drawable.carta20))
        mazo.add(Imagen(R.drawable.carta21))
        mazo.add(Imagen(R.drawable.carta22))
        mazo.add(Imagen(R.drawable.carta23))
        mazo.add(Imagen(R.drawable.carta24))
        mazo.add(Imagen(R.drawable.carta25))
        mazo.add(Imagen(R.drawable.carta26))
        mazo.add(Imagen(R.drawable.carta27))
        mazo.add(Imagen(R.drawable.carta28))
        mazo.add(Imagen(R.drawable.carta29))
        mazo.add(Imagen(R.drawable.carta30))
        mazo.add(Imagen(R.drawable.carta31))
        mazo.add(Imagen(R.drawable.carta32))
        mazo.add(Imagen(R.drawable.carta33))
        mazo.add(Imagen(R.drawable.carta34))
        mazo.add(Imagen(R.drawable.carta35))
        mazo.add(Imagen(R.drawable.carta36))
        mazo.add(Imagen(R.drawable.carta37))
        mazo.add(Imagen(R.drawable.carta38))
        mazo.add(Imagen(R.drawable.carta39))
        mazo.add(Imagen(R.drawable.carta40))
        mazo.add(Imagen(R.drawable.carta41))
        mazo.add(Imagen(R.drawable.carta42))
        mazo.add(Imagen(R.drawable.carta43))
        mazo.add(Imagen(R.drawable.carta44))
        mazo.add(Imagen(R.drawable.carta45))
        mazo.add(Imagen(R.drawable.carta46))
        mazo.add(Imagen(R.drawable.carta47))
        mazo.add(Imagen(R.drawable.carta48))
        mazo.add(Imagen(R.drawable.carta49))
        mazo.add(Imagen(R.drawable.carta50))
        mazo.add(Imagen(R.drawable.carta51))
        mazo.add(Imagen(R.drawable.carta52))
        mazo.add(Imagen(R.drawable.carta53))
        mazo.add(Imagen(R.drawable.carta54))
    }
    */
}

class Hilo (Etiqueta: TextView) : Thread(){
    var cont=0
    var etiquetaGlobal= Etiqueta
    override fun run() {
        super.run()
        while (cont<55){
            etiquetaGlobal.text="Numero de carta: ${cont++} de 54 "
            sleep(1000)
        }
        etiquetaGlobal.text=""
    }
}

