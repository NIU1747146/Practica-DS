package baseNoStates;

/**
 * Clase abstracta que define la interfaz común para el patrón de diseño State.
 *
 * Se aplica el patrón State para encapsular el comportamiento dependiente del estado
 * de una puerta (Unlocked, Locked, UnlockedShortly, Propped). De esta forma se eliminan
 * bloques condicionales 'if-else' o 'switch' complejos dentro de la clase Door,
 * haciendo el sistema fácilmente extensible si en el futuro se añaden nuevos estados
 * (como "Do Not Disturb" o "Idle") sin modificar el código existente.
 */

public abstract class DoorState {
  protected Door door;
  protected String name;

  public DoorState(Door door) {
    this.door = door;
  }

  public String getName() { return name; }

  public void open() {}
  public void close() {}
  public void lock() {}
  public void unlock() {}
  public void unlockShortly() {}

}
