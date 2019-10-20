package com.bookworms.library.acceptanceTest;

import com.bookworms.library.web.LibraryApplication;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

@WebAppConfiguration
@ContextConfiguration(classes = LibraryApplication.class)
abstract class ApplicationContextConfiguration {
}
