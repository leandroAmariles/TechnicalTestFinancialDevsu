package com.devsu.audittransactions.infraestructure.adapters.controller.dto.in;

import java.util.List;

public record ExtractRequest(

        String clientName,

        String startDate,

        String endDate
) {
}
