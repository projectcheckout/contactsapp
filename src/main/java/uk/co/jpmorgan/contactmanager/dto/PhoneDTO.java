package uk.co.jpmorgan.contactmanager.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uk.co.jpmorgan.contactmanager.validator.ValidatePhone;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({ "internationalDialCode", "phoneNumber", "extensionNumber" })
@ValidatePhone
public class PhoneDTO {
	@JsonProperty("internationalDialCode")
	@NotNull(message = "International dialing code cannot be blank")
	@ApiModelProperty(example = "+44", required = true)
	private String internationalDialCode;

	@JsonProperty("phoneNumber")
	@NotNull(message = "Phone number cannot be null")
	@ApiModelProperty(example = "7455555544", required = true)
	private Long phoneNumber;

	@JsonProperty("extensionNumber")
	private Integer extensionNumber;
}
