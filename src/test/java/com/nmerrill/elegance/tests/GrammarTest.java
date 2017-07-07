package com.nmerrill.elegance.tests;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import com.google.common.reflect.ClassPath;
import com.google.inject.Inject;
import com.nmerrill.elegance.TestModule;
import com.nmerrill.elegance.generation.EleganceParserFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Guice(modules = TestModule.class)
public class GrammarTest {

    @Inject
    private EleganceParserFactory parser;

    @DataProvider(name = "sampleCode")
    public Object[][] sampleCode() throws IOException {
        List<ClassPath.ResourceInfo> resources = ClassPath.from(getClass().getClassLoader()).getResources()
                .stream()
                .filter(resourceInfo -> resourceInfo.getResourceName().startsWith("com/nmerrill/elegance/testcode"))
                .collect(Collectors.toList());

        Object[][] grammars = new Object[resources.size()][1];
        for (int i = 0; i < resources.size(); i++){
            grammars[i][0] = Resources.toString(resources.get(i).url(), Charsets.UTF_8);
        }
        return grammars;
    }


    /**
     * Iterates over all test code, and ensures that the grammar can still handle it
     */
    @Test(dataProvider = "sampleCode")
    public void testSampleCode(String code){
        parser.getParser(code).prog();
    }
}
