package com.example.daggerdemo.auto.dagger.inject

import javax.inject.Inject

class InjectViewModel @Inject constructor(val userRepository: UserRepository)