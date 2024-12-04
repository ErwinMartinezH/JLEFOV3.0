package ConversionesSemanticas;

public class Lista{
         String pro;
        char pila;
         int estado;

        // Constructor
        public Lista(int miInt ,String miString, char miChar) {
            this.pro = miString;
            this.pila = miChar;
            this.estado = miInt;
        }

        // Getter para miString
        public String getPro() {
            return pro;
        }

        // Setter para miString
        public void setPro(String pro) {
            this.pro = pro;
        }

        // Getter para miChar
        public char getPila() {
            return pila;
        }

        // Setter para miChar
        public void setPila(char pila) {
            this.pila = pila;
        }

        // Getter para miInt
        public int getEstado() {
            return estado;
        }

        // Setter para miInt
        public void setEstado(int estado) {
            this.estado = estado;
        }

}
