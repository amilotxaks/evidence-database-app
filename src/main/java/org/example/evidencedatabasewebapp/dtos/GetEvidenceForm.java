package org.example.evidencedatabasewebapp.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetEvidenceForm {
    @NotBlank
    @Pattern(regexp = "[A-Z]-\\d{2}-[A-Z]{2}", message = "Неверный формат. Пример: B-25-KL")
    private String caseNumber;
}
