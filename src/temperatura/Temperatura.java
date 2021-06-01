package temperatura;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Temperatura {

    float temperatura;
    PropertyChangeSupport conector;

    public Temperatura(){
        this.temperatura = 0;
        this.conector = new PropertyChangeSupport(this);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.conector.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        this.conector.removePropertyChangeListener(listener);
    }

    public float getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(float temperatura) {
        this.temperatura = temperatura;
        conector.firePropertyChange("Cambiado", null, this.toString());
    }

    @Override
    public String toString() {
        return temperatura + "C";
    }
}
