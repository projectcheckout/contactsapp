package uk.co.jpmorgan.contactmanager.validator;

import static org.mockito.ArgumentMatchers.any;

import javax.validation.ConstraintValidatorContext;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import uk.co.jpmorgan.contactmanager.dto.PhoneDTO;

@ExtendWith(MockitoExtension.class)
public class PhoneValidatorTest {
	@Mock
	ConstraintValidatorContext.ConstraintViolationBuilder constraintViolationBuilder;
	@Mock
	private ConstraintValidatorContext context;
	private PhoneValidator phoneValidator = new PhoneValidator();

	@Captor
	ArgumentCaptor<String> errorMessage;

	@Test
	public void shouldReturnInternationalDialCodeErrorWhenNotValid() {
		PhoneDTO phoneDTO = PhoneDTO.builder().internationalDialCode("44").build();
		Mockito.when(context.buildConstraintViolationWithTemplate(any(String.class)))
				.thenReturn(constraintViolationBuilder);

		phoneValidator.isValid(phoneDTO, context);
		Mockito.verify(context).buildConstraintViolationWithTemplate(errorMessage.capture());
		Assertions.assertEquals("International dial code 44 is not valid", errorMessage.getValue());
	}

	@Test
	public void shouldReturnPhoneNumberErrorWhenNotValid() {
		PhoneDTO phoneDTO = PhoneDTO.builder().internationalDialCode("+44").phoneNumber(74665788L).build();
		Mockito.when(context.buildConstraintViolationWithTemplate(any(String.class)))
				.thenReturn(constraintViolationBuilder);

		phoneValidator.isValid(phoneDTO, context);
		Mockito.verify(context).buildConstraintViolationWithTemplate(errorMessage.capture());
		Assertions.assertEquals("Phone number 74665788 is not valid", errorMessage.getValue());
	}

	@Test
	public void shouldReturnNoErrorWhenValuesValid() {
		PhoneDTO phoneDTO = PhoneDTO.builder().internationalDialCode("+44").phoneNumber(7566314543L).build();
		boolean isValid = phoneValidator.isValid(phoneDTO, context);
		Assertions.assertEquals(true, isValid);
	}
}
