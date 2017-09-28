package com.loreans.repository

import com.loreans.views.PlayerView
import org.springframework.data.repository.CrudRepository

interface
PlayerViewRepository : CrudRepository<PlayerView, String>