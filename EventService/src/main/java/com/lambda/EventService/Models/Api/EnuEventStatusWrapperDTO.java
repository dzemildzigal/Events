package com.lambda.EventService.Models.Api;

import com.lambda.EventService.Models.Entity.EnuEventStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EnuEventStatusWrapperDTO {
    private Long userId;
    private EnuEventStatus enuEventStatus;
}
