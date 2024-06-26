package org.backend.evidencedatabasewebapp.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddEvidenceForm {
    @NotBlank
    @Pattern(regexp = "[A-Z]-\\d{2}-[A-Z]{2}", message = "Неверный формат. Пример: B-25-KL")
    private String caseNumber;

    @NotBlank
    private String accused;

    @NotBlank
    private String reason;

    @NotBlank
    private String victim;

    @NotBlank
    private String caseType;

    @NotBlank
    private String severity;

    @NotBlank
    private String caseDescription;
}
