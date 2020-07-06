package Clases;

public abstract class Persona {

    protected int noCuenta;
    protected String nombre;
    protected String apellido;
    protected String direccion;
    protected int noIdentidad;
    protected String sexo;
    protected String estadoCivil;
    protected int telefono;
    protected String correo;
    protected String fechaNacimiento;

    public abstract String getfechaNacimiento();
    public abstract void setfechaNacimiento(String fecha);
    public abstract void setCorreo(String correo);
    public abstract String getCorreo();
    public abstract String getSexo();
    public abstract void setSexo(String sexo);
    public abstract String getEstadoCivil();
    public abstract void setEstadoCivil(String estadoCivil);
    public abstract int getTelefono();
    public abstract void setTelefono(int telefono);
    public abstract int getNoCuenta();
    public abstract void setNoCuenta(int noCuenta);
    public abstract String getNombre();
    public abstract void setNombre(String nombre);
    public abstract String getApellido();
    public abstract void setApellido(String apellido);
    public abstract String getDireccion();
    public abstract void setDireccion(String direccion);
    public abstract int getNoIdentidad();
    public abstract void setNoIdentidad(int noIdentidad);
}
