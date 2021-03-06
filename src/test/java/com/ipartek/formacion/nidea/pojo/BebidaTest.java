package com.ipartek.formacion.nidea.pojo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Iterator;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Test;

public class BebidaTest {

	@Test
	public void test() {
		try {
			// Crear Factoria y Validador
			ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
			Validator validator = factory.getValidator();

			// Crear Objeto a validar
			Bebida b1 = new Bebida();

			// Obtener las ConstrainViolation
			Set<ConstraintViolation<Bebida>> violations = validator.validate(b1);
			assertTrue(violations.size() == 2);

			b1.setPrecio(0.2f);
			b1.setNombre("ab");
			violations = validator.validate(b1);
			assertTrue(violations.size() == 1);

			Iterator<ConstraintViolation<Bebida>> it = violations.iterator();
			ConstraintViolation<Bebida> violation = null;
			while (it.hasNext()) {
				violation = it.next();
				assertEquals("Tiene que ser la propiedad nombre", "nombre", violation.getPropertyPath().toString());
				assertEquals("ab", violation.getInvalidValue());
				// violation.getMessage();
			}
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}

	}

}
