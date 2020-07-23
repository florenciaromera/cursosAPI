package ar.com.ada.api.cursos.entities;

public class Pais {
    /**
     * An enum is a special "class" that represents a group of constants. Use enums
     * when you have values that you know aren't going to change. An enum can, just
     * like a class, have attributes and methods. The only difference is that enum
     * constants are public, static and final (unchangeable - cannot be overridden).
     * An enum cannot be used to create objects, and it cannot extend other classes
     * (but it can implement interfaces).
     */
    public enum PaisEnum {
        /**
         * First line inside enum should be list of constants and then other things like
         * methods, variables and constructor.
         */
        ARGENTINA(32), VENEZUELA(840), ESTADOS_UNIDOS(862);

        // el enum en java tiene un numero asociado, ese value es el numero que asocio
        // entre parentesis
        // es el valor que lleva el enumerado
        private final Integer value;

        // NOTE: Enum constructor tiene que estar en privado
        private PaisEnum(Integer value) {
            this.value = value;
        }

        public Integer getValue() {
            return value;
        }

        // Parsing is to read the value of one object to convert it to another type.
        public static PaisEnum parse(Integer id) {
            PaisEnum status = null; // Default
            for (PaisEnum item : PaisEnum.values()) {
                if (item.getValue().equals(id)) {
                    status = item;
                    break;
                }
            }
            return status;
        }
    }

    public enum TipoDocuEnum {
        DNI(1), PASAPORTE(2);

        private final Integer value;

        // NOTE: Enum constructor tiene que estar en privado
        private TipoDocuEnum(Integer value) {
            this.value = value;
        }

        public Integer getValue() {
            return value;
        }

        public static TipoDocuEnum parse(Integer id) {
            TipoDocuEnum status = null; // Default
            for (TipoDocuEnum item : TipoDocuEnum.values()) {
                if (item.getValue().equals(id)) {
                    status = item;
                    break;
                }
            }
            return status;
        }
    }

}