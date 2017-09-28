package com.loreans.views

import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class PlayerView(@Id val id: String, val name: String, val skill:Int)