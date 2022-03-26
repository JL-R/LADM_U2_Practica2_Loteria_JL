package mx.tecnm.tepic.ladm_u2_practica2_loteria


import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle


import kotlinx.coroutines.*
import mx.tecnm.tepic.ladm_u2_practica2_loteria.databinding.ActivityMainBinding
import kotlin.coroutines.EmptyCoroutineContext


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    //profe por alguna razón siempre hay una carta que dice hasta la mitad pero no truena,
    // continua como si nada a la siguiente carta, tal vez es por el tiempo pero es siempre en
    // diferente carta

    // no implementé hilos, solo me faltó eso
    // declaro mi array de tipo imagen con las 54 cartas, poniendo titulo, imagen y audio
    var mazo: Array <Imagen> = arrayOf(
        Imagen("El Gallo", R.drawable.carta1, R.raw.gallo),
        Imagen("El Diablito", R.drawable.carta2, R.raw.diablo) ,
        Imagen("La Dama", R.drawable.carta3, R.raw.dama),
        Imagen("El catrín", R.drawable.carta4, R.raw.catrin),
        Imagen("El Paraguas", R.drawable.carta5,R.raw.paraguas),
        Imagen("La sirena", R.drawable.carta6, R.raw.sirena),
        Imagen("La escalera", R.drawable.carta7, R.raw.escalera),
        Imagen("La botella", R.drawable.carta8, R.raw.botella),
        Imagen("El barril", R.drawable.carta9, R.raw.barril),
        Imagen("El arbol", R.drawable.carta10, R.raw.arbol),
        Imagen("El melon", R.drawable.carta11, R.raw.melon),
        Imagen("El valiente", R.drawable.carta12,R.raw.valiente),
        Imagen("El gorrito", R.drawable.carta13, R.raw.gorrito),
        Imagen("La muerte", R.drawable.carta14, R.raw.muerte),
        Imagen("La pera", R.drawable.carta15, R.raw.pera),
        Imagen("La bandera", R.drawable.carta16,R.raw.bandera),
        Imagen("El bandolón", R.drawable.carta17, R.raw.bandolon),
        Imagen("El violoncello", R.drawable.carta18, R.raw.violoncello),
        Imagen("La Garza", R.drawable.carta19, R.raw.garza),
        Imagen("El pajaro", R.drawable.carta20, R.raw.pajaro),
        Imagen("La mano", R.drawable.carta21, R.raw.mano),
        Imagen("La bota", R.drawable.carta22, R.raw.bota),
        Imagen("La luna", R.drawable.carta23, R.raw.luna),
        Imagen("El cotorro", R.drawable.carta24, R.raw.cotorro),
        Imagen("El borracho", R.drawable.carta25, R.raw.borracho),
        Imagen("El negrito", R.drawable.carta26, R.raw.negrito),
        Imagen("El corazon", R.drawable.carta27, R.raw.corazon),
        Imagen("La sandía", R.drawable.carta28, R.raw.sandia),
        Imagen("El tambór", R.drawable.carta29, R.raw.tambor),
        Imagen("El camarón", R.drawable.carta30, R.raw.camaron),
        Imagen("Las jaras", R.drawable.carta31, R.raw.jaras),
        Imagen("El musico", R.drawable.carta32, R.raw.musico),
        Imagen("La araña", R.drawable.carta33, R.raw.arana),
        Imagen("El soldado", R.drawable.carta34, R.raw.soldado),
        Imagen("La estrella", R.drawable.carta35, R.raw.estrella),
        Imagen("El cazo", R.drawable.carta36, R.raw.cazo),
        Imagen("El mundo", R.drawable.carta37, R.raw.mundo),
        Imagen("El apache", R.drawable.carta38, R.raw.apache),
        Imagen("El nopal", R.drawable.carta39, R.raw.nopal),
        Imagen("El alacrán", R.drawable.carta40, R.raw.alacran),
        Imagen("La rosa", R.drawable.carta41, R.raw.rosa),
        Imagen("La calavera", R.drawable.carta42, R.raw.calavera),
        Imagen("La campana", R.drawable.carta43, R.raw.campana),
        Imagen("El cantarito", R.drawable.carta44, R.raw.cantarito),
        Imagen("El venado", R.drawable.carta45, R.raw.venado),
        Imagen("El sol", R.drawable.carta46, R.raw.sol),
        Imagen("La corona", R.drawable.carta47, R.raw.corona),
        Imagen("La chalupa", R.drawable.carta48, R.raw.chalupa),
        Imagen("EL pino", R.drawable.carta49, R.raw.pino),
        Imagen("EL pescado", R.drawable.carta50, R.raw.pescado),
        Imagen("La palma", R.drawable.carta51, R.raw.palma),
        Imagen("La maceta", R.drawable.carta52, R.raw.maceta),
        Imagen("El arpa", R.drawable.carta53, R.raw.arpa),
        Imagen("La rana", R.drawable.carta54, R.raw.rana),
    )
    var contador=0
    var proceso= true /*declaro proceso para poder manipular el botón iniciar, si termina de mencionar
    todas las cartas entonces está a la espera de que le de nuevamente iniciar y vuelva a iniciar
    la corrutina
    */
    var pausar= false /* para el boton suspender hasta que le de verificar va a continuar con las
    cartas que quedaban en el mazo mostrando un settittle de las cartas faltantes
    */
    val scope= CoroutineScope(Job() + Dispatchers.Main)

    val objetoCoroutineControlada= scope.launch(EmptyCoroutineContext, CoroutineStart.LAZY){
        while (true){
            runOnUiThread {
                if(!pausar){ /*cuando pausa sea=true no va a mencionar la siguiente carta ni
                    mostrar la imagen ni eitqueta, pero se va a mantener en el while*/

                    //menciono audio, muestro carta y texto de my array mazo
                    MediaPlayer.create(this@MainActivity,mazo[contador].audio).start()
                    binding.imagenLoteria.setImageResource(mazo[contador].carta)
                    binding.etLoteria.text=mazo[contador++].name
                }
            }
            delay(3000L)
            /*la ultima carta se mostrará al final dando a interpretar que ya terminó porque ya
             no suena, tambien puse 2 condiciones para que tambien en pausar no resetié la imagen
             tronando el programa*/
            if(contador<mazo.size && !pausar) {
                MediaPlayer.create(this@MainActivity,mazo[contador].audio).reset()
            }

            /*Cuando el contador sea igual al tamaño del mazo pues se "pausa" para que
            no entren a los ifs y habilito, le pongo al usuario si quiere nuevo juego a la espera
            de que el botoón iniciar nuevamente cambia el valor de las variables manipuladoras
            y vuelva a barajear para mostrar las cartas
            */
            if(contador== mazo.size){
                pausar=true
                proceso=false
                setTitle("¿Nuevo Juego?")
                binding.btnIniciar.isEnabled=true
                binding.btnverificar.isEnabled=false
                binding.btnSuspender.isEnabled=false
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*el boton iniciar tiene 1 condición, inicio la corrutina por primera vez, luego de que
        ya pasaran todas las cartas pues está a la espera volver a presionar iniciar reiniciando
        el contador para un nuevo barajeo volviendo las variables manipulables a como estaban*/
        binding.btnIniciar.setOnClickListener {
            if (proceso){
                aleatorio(mazo)
                objetoCoroutineControlada.start()
                binding.btnSuspender.isEnabled=true
            }else{
                setTitle("se va y se corre con la vieja del pozole")
                if(contador== mazo.size){
                    contador=0
                    pausar=false
                    proceso=true
                    aleatorio(mazo)
                }
            }
            binding.btnSuspender.isEnabled=true
        }

        /*pausar lo pongo a true para que se mantenga en el while sin hacer nada a la espera
        * de presionar el botón verificar*/
        binding.btnSuspender.setOnClickListener {
            binding.btnIniciar.isEnabled=false
            binding.btnSuspender.isEnabled=false
            if(objetoCoroutineControlada.isActive){
                setTitle("LOTERIA!!")
                binding.btnverificar.isEnabled=true
                pausar=true
                return@setOnClickListener
            }

        }
        /*verificar muestra las demas cartas que quedaban en el mazo cambiando el titulo
        * a cartas faltantes*/
        binding.btnverificar.setOnClickListener {
            setTitle("Cartas Faltantes")
            binding.btnIniciar.isEnabled=false
            binding.btnSuspender.isEnabled=false
            pausar=false
            return@setOnClickListener

        }
    }
    // funciona para barajear el mazo a la barata como los precios bajos de bodega aurrera
    fun aleatorio(lista: Array<Imagen>){
        lista.shuffle()
    }

}