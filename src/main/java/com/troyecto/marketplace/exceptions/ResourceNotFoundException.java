package com.troyecto.marketplace.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

/*
 @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) //para evitar problemas de carga con el fetchType
 OnetoOne, definir entidad dueño y entidad esclavo, entidad dueño (@JoinColumn), entidad esclavo, si es bidireccional (mappedby "")
 siempre indicar fetchTpte.LAZY y Cascade.ALL en el dueño, el dueño debe tener la llave foranea
 Luego se edita el DTO, Mapper y serviceImpl

public void setAddress(Address address) { en dueño
        this.address = address;
        if (address != null && address.getUser() != this) {
            address.setUser(this);
        }
    }

 Para el front, se modifica el payload, el handleSubmit y el useState añadiendo de forma textual la nueva entidad
 addres: {
                    street: data.addres.street,
                    city: data.addres.city,
                    state: data.addres.state,
                    country: data.addres.country,
                    postalCode: data.addres.postalCode,
                }
  hacer un handleChange selectivo para los atributos de la nueva entidad
  if (["street", "city", "state", "country", "postalCode"].includes(name)) {
            setEmployee((prev) => ({
                ...prev,
                addres: {
                    ...prev.addres,
                    [name]: value,
                },
            }));
        } else {
            // Si es cualquier otro campo, lo actualiza directamente en employee
            setEmployee((prev) => ({
                ...prev,
                [name]: value,
            }));
        }

  Para el formulario basta con hacer un segundo formulario y llenarlo como de costumbre, eg:
  <h4>Address Information</h4>

                <div>
                <div className="form-group">
                    <label className="form-label">Street</label>
                    <input
                        type="text"
                        className="form-control"
                        name="street"
                        value={employee.addres.street}
                        onChange={handleChange}
                    />
                </div>
 */